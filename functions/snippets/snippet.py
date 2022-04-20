import os

from . import menus
from . import fields
from util import check_util
from ..template import line_spliter

def short_desc():
    hint = "snippet 根据模板创建代码片段，放到剪贴板 \n"
    hint += "    默认查找 .hint 下的 snippet.txt 文件查找定义\n"
    hint += "    snippet snip_name \n"
    return hint

# 输入参数检查
def check_input(args):
    input_file = '.hint/snippet.txt'
    # 用默认 .hint/snippet.txt 文件
    check_util.file_exists(input_file)
    snip_name = None
    if(len(args) > 0): 
        snip_name = args[0]
    create_snippet(input_file, snip_name)


def create_snippet(filename, snip_name):
    units = line_spliter.parse_file(filename)
    for unit in units:
        if snip_name == None or snip_name == unit.name:
            create_single_snippet(unit, unit.name)


def create_single_snippet(unit, snippet_type):
    if snippet_type == 'menu':
        menus.create(unit)
    elif snippet_type == 'field':
        fields.create(unit)

