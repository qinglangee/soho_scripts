import os
import yaml

from . import menus
from . import fields
from . import custom
from util import check_util
from ..template import line_spliter
from util import file_util

def short_desc():
    hint = "snippet 根据模板创建代码片段，放到剪贴板 \n"
    hint += "    默认查找 .hint 下的 snippet.txt 文件查找定义\n"
    hint += "    snippet snip_name  # 不传 name 就生成所有的\n"
    return hint

# 输入参数检查
def check_input(args):
    input_file = '.hint/snippet.txt'
    # 用默认 .hint/snippet.txt 文件

    # TODO  这块儿有时间改改吧， 统一一下  

    # 如果有，优先用 yml
    if os.path.exists('.hint/snippet.yml'):
        input_file = '.hint/snippet.yml'
    check_util.file_exists(input_file)
    snip_name = None
    if(len(args) > 0): 
        snip_name = args[0]
    create_snippet(input_file, snip_name)


def create_snippet(filename, snip_name):
    print("read file ", filename)
    # 新格式的配置文件用 yaml 文件
    if filename.endswith('.yml') or filename.endswith('.yaml'):
        content = file_util.load_yaml(filename)
        snippets = content['snippets']
        for unit in snippets:
            if snip_name == None or snip_name == unit['name']:
                create_single_snippet(unit, unit['name'])


    else:  # 旧格式的配置文件
        units = line_spliter.parse_file(filename)
        for unit in units:
            if snip_name == None or snip_name == unit.name:
                create_single_snippet(unit, unit.name)


def create_single_snippet(unit, snippet_type):
    if snippet_type == 'menu':
        menus.create(unit)
    elif snippet_type == 'field':
        fields.create(unit)
    elif snippet_type == 'custom':
        custom.create(unit)
