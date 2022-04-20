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
    text = file_util.read_file(filename)
    start = rf'// *{flag_str} *start\n'
    end = rf'// *{flag_str} *end\n'
    replacement = f'// {flag_str} start\n' + content + f'// {flag_str} end\n'
    replaced = re.sub(start + r'.*' + end, replacement, text, flags=re.DOTALL)
    file_util.write_file(filename, replaced)
