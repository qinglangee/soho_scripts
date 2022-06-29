from re import sub
import sys

from functions import init
from functions import str_replace
from functions.create_class import factory
from functions.create_code import code_template
from functions import copy_file, backup, tishi
from functions.snippets import snippet
from functions.vscode import vscode


sub_cmds = {
    "init":init,  ## 复制初始化模板文件
    "rep":str_replace, ## 文件内容替换
    "create":factory, ## 根据模板创建 bean 文件
    "cp":copy_file, ## copy 文件模板
    "backup":backup,  ## 备份文件
    "tishi":tishi, ## 命令用法提示
    "code":code_template, ## 命令用法提示
    "snippet":snippet, ## 生成代码片段
    "vs":vscode, ## vscode 配置修改
}

def print_help():
    hint = "请输入正确的参数。soho subcmd par1 par2 ...\n"
    hint += "subcmd 子命令有下列选项：\n"
    print(hint)
    for key in sub_cmds:
        print(sub_cmds[key].short_desc())

def input_process():
    args = sys.argv
    # 至少要3个参数的 # 第一个参数是脚本的路径
    if(len(args) < 2): 
        print_help()
        print("参数数量太少。。。")
        return
    subcmd = args[1]
    print("subcmd ", subcmd, args[2:])

    if subcmd in sub_cmds:
        sub_cmds[subcmd].check_input(args[2:])
    else:
        print_help()
        print("=======没有对应的子命令=======")

input_process()