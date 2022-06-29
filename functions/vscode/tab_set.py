import os
from util import file_util
from util import string_util

setting_file = '.vscode/settings.json'

def tab(args):
    if len(args) == 0 or args[0] == 'add':
        confirm_dir()
        add_tab_setting()
    else:
        remove_tab_setting()

# 目录不存在就创建
def confirm_dir():
    if not os.path.exists('.vscode/'):
        os.mkdir('.vscode')

def add_tab_setting():
    if not os.path.exists(setting_file):
        file_util.write_file(setting_file, '{\n"editor.tabSize": 4,\n}\n')
    else:
        string_util.replace_file_line_by_flag(setting_file, '({)', '\\1\n"editor.tabSize": 4,\n')

def remove_tab_setting():
    if not os.path.exists(setting_file):
        return
    else:
        string_util.delete_file_line_by_flag(setting_file, 'editor.tabSize')
