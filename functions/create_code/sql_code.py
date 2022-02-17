import os
from util import file_util as fu
from ..template import parser
from ..template import line_spliter
import config as c

mysql_dict = {"int":"INT", 'str':'VARCHAR', 'time':'DATETIME'}
sqlite_dict = {"int":"INTEGER", 'str':'TEXT', 'time':'TIMESTAMP'}
common_dict = {'nn':'NOT NULL', 'null':'NULL', 'pk':'PRIMARY KEY', 'ai':'AUTOINCREMENT'}

tpl_snippets = []
setting = {}
type_dict = {}

# 读取页面模板，组装行列字段
def process(src_dir, filename):
    global tpl_snippets
    global setting
    global type_dict

    # 初始化几个全局变量
    tpl_snippets = parser.parse_file(os.path.join(c.code_template_dir, "sql/sql_tpl.txt"))
    units = line_spliter.parse_file(filename)
    setting = units[0]
    sql_type = setting.get_field('type')
    if sql_type == 'mysql':
        type_dict = mysql_dict
    elif sql_type == 'sqlite':
        type_dict = sqlite_dict
    for key in common_dict:
        type_dict[key] = common_dict[key]


    content = ""
    for unit in units[1:]:
        content += create_table(unit)

    fu.write_file(os.path.join(src_dir, setting.get_field('output')), content)

def create_table(unit):
    content = ""
    for line in unit.lines:

        field_dict = {}
        if line[0].startswith('*'):
            field_dict['name'] = line[1]
            content += special_line(line)
        else:
            field_dict['name'] = line[0]
            field_dict['type'] = search_dict(line[1])
            field_dict['length'] = get_length(line)
            field_dict['other'] = get_more(line)
            content += tpl_snippets['column'].replace(field_dict)

    if content.endswith(",\n"):
        content = content[:-2]  # 去掉最后的一个逗号和换行
    table_text = tpl_snippets['table'].replace({'table_name':unit.name, 'content': content})
    return table_text

def search_dict(name):
    if name in type_dict:
        return type_dict[name]
    else:
        return name
# 默认一行最多三部分，最后一部分要么是长度 ， 要么是原样保存
def get_length(line):
    if len(line) > 2 and line[2].isdigit():
        return '('+line[2]+')'
    else:
        return ''
# 转换从第3项往后的部分
def get_more(line):
    text = ''

    if len(line) > 2 and not line[2].isdigit():
        text = search_dict(line[2])
    if len(line) > 3:
        for key in line[3:]:
            value = search_dict(key)
            if value != '':
                text += ' ' + value
    if text != '':
        text = ' ' + text
    return text

# 非一般字段的行
def special_line(line):
    if line[0] == '*pk':
        return tpl_snippets['pk'].replace_by_list(line[1:])
    elif line[0] == '*fk':
        return tpl_snippets['fk'].replace_by_list(line[1:])
    elif line[0] == '*zh_keep':
        return '    ' + line[1]

    return ''
