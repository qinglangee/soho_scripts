import os
from util import file_util as fu
from ..template import parser
from ..template import line_spliter
import config as c

mysql_dict = {"int":"INT", 'str':'VARCHAR', 'time':'DATETIME', 'ai':'AUTO_INCREMENT'}
sqlite_dict = {"int":"INTEGER", 'str':'TEXT', 'time':'TIMESTAMP', 'ai':'AUTOINCREMENT'}
common_dict = {'nn':'NOT NULL', 'null':'NULL', 'pk':'PRIMARY KEY'}

bean_type_dict = {'int':'int', 'str':'String','time':'Date'}

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
    setting, units = line_spliter.parse_with_setting(filename)
    sql_type = setting.get_field('type')
    if sql_type == 'mysql':
        type_dict = mysql_dict
    elif sql_type == 'sqlite':
        type_dict = sqlite_dict
    for key in common_dict:
        type_dict[key] = common_dict[key]


    content = ""
    for unit in units:
        content += create_table(unit)
    # 生成的 sql 语句内容输出到文件
    fu.write_file(os.path.join(src_dir, setting.get_field('output')), content)

    # 如果需要创建转换类就创建一下
    if(setting.has_field('converter')):
        create_converter(src_dir, units)

# 把一个表的定义信息转换面建表语句
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
        return '    ' + line[1] + "\n"

    return ''



# 创建 data map 转换成 bean 的类
def create_converter(src_dir, units):
    global setting

    methods = ""
    for unit in units:
        methods += create_method(unit)

    field_dict = {
        'package': setting.get_field('package'),
        'convert_methods':methods
    }
    content = tpl_snippets['beanfile'].replace(field_dict)
    # 生成的 sql 语句内容输出到文件
    fu.write_file(os.path.join(src_dir, setting.get_field('converter')), content)

# 把表定义 unit 转换成方法代码
def create_method(unit):
    content = ""
    field_dict = {}
    field_dict['bean_var'] = name_convert(unit.name)
    for line in unit.items:
        field_dict['method_name'] = 'set' + name_convert(line[0]).title()
        field_dict['data_type'] = convert_bean_type(line[1])
        field_dict['col_name'] = line[0].upper()

        content += tpl_snippets['set_statement'].replace(field_dict)
    method_dict = {}
    method_dict['bean_name'] = name_convert(unit.name).title()
    method_dict['bean_var'] = name_convert(unit.name)
    method_dict['set_statements'] = content
    method_text = tpl_snippets['convert_method'].replace(method_dict)
    return method_text

# 下划线分隔的字符串换成驼峰形式
def name_convert(name):
    parts = name.split("_")
    result = parts[0]
    for part in parts[1:]:
        result += part.title()
    return result

# unit 中的类型转换成 java 类型
def convert_bean_type(name):
    if name in bean_type_dict:
        return bean_type_dict[name]
    else:
        return name