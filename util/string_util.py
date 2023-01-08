import re
import os
from . import file_util

# if s1 contains s2
def contains(s1, s2):
    return s2 in s1

def upper01(src):
    '''Convert someStr to SomeStr 首字符变大写'''
    if len(src) < 1:
        return src
    return src[0].upper() + src[1:]

def package_to_dir(src):
    '''Convert com.bean.manage to com/bean/manage'''
    return src.replace(".", "/")

def indent(input, count=1, tab_size=4):
    '''把整个字符串缩进 count 次数'''
    tab = ' ' * tab_size  # 4个空格
    result = input
    for i in range(count):
        result = tab + result.replace('\n', '\n' + tab)
    return result

def replace_by_flag(filename, flag_str, content):
    '''根据标记，简单替换 start end 标记中间部分的内容'''
    text = file_util.read_file(filename)
    start = rf'// *{flag_str} *start.*\n'   # 正则   //    flag_str    start
    end = rf'// *{flag_str} *end.*\n'
    replacement = f'// {flag_str} start\n' + content + f'// {flag_str} end\n'
    # re.DOTALL 会匹配换行符，可以替换多行
    replaced = re.sub(start + r'.*' + end, replacement, text, flags=re.DOTALL)
    file_util.write_file(filename, replaced)

def replace_lines_between_flag(filename, flag_str, content):
    '''根据标记，替换 start end 标记行中间部分的内容'''
    lines = file_util.read_lines(filename)
    new_lines = []
    start = rf'.*{flag_str} *start'   # 正则     flag_str    start
    end = rf'.*{flag_str} *end'
    replace = False
    for line in lines:
        if not replace:
            new_lines.append(line)
        if re.match(start, line):
            replace = True
            new_lines.append(content)
        if re.match(end, line):
            replace = False
            new_lines.append(line)

    file_util.write_list(filename, new_lines)

def delete_line_by_flag(origin, flag_str, total=False):
    '''删除符合 flag 标记的行内容'''
    reg_text = rf'.*{flag_str}.*\n' if not total else flag_str
    replaced = re.sub(reg_text, '', origin)
    return replaced

def delete_file_line_by_flag(filename, flag_str):
    '''删除文件中符合 flag 标记的行内容'''
    text = file_util.read_file(filename)
    replaced = delete_line_by_flag(text, flag_str)
    file_util.write_file(filename, replaced)

def replace_line_by_flag(origin, flag_str, content, total=False):
    '''替换符合 flag 标记的行内容
    在 re.sub() 中 ， content 用 \g<1> 来引用匹配到的组
    ('abcde', '(bc)', '\\1mm'), 替换结果为 abcmmde
    '''
    reg_text = rf'.*{flag_str}.*\n' if not total else flag_str
    replaced = re.sub(reg_text, content, origin)
    return replaced


def replace_file_line_by_flag(filename, flag_str, content):
    '''替换文件中符合 flag 标记的行内容'''
    text = file_util.read_file(filename)
    replaced = replace_line_by_flag(text, flag_str, content)
    file_util.write_file(filename, replaced)