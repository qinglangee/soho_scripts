import sys

from functions import init
from functions import str_replace
from functions.create_class import factory
from functions import copy_file


def printHelp():
    hint = "请输入正确的参数。soho subcmd par1 par2 ...\n"
    hint += "subcmd 子命令有下列选项：\n"
    hint += "init 复制模板: 参数 1 目录 2 类型 3 序号  \n"
    hint += "    init . c++ 02\n"
    hint += "rep 内容替换: 参数 1 替换的目录或文件 2 替换内容文件  \n"
    hint += "    rep ./src rep.txt\n"
    hint += "create 根据模板创建文件: 参数 1 要扫描的目录 \n"
    hint += "    create ./src\n"
    hint += "cp 复制模板文件: 参数 1 . 当前目录 2 要cp的分类目录 3 分类的子目录 \n"
    hint += "    cp . java util  # cp 文件到 zh_demo 目录\n"
    print(hint)

def inputProcess():
    args = sys.argv
    # print("---", args)
    # 至少要3个参数的 # 第一个参数是脚本的路径
    if(len(args) < 2): 
        printHelp()
        print("参数数量太少。。。")
        return
    subcmd = args[1]
    print("subcmd ", subcmd, args[2:])

    if subcmd == "init":
        init.check_input(args[2:])  ## 复制模板文件
    elif subcmd == "rep":
        str_replace.checkInput(args[2:])  ## 文件内容替换
    elif subcmd == "create":
        factory.checkInput(args[2:])  ## 文件内容替换
    elif subcmd == "cp":
        copy_file.check_input(args[2:])  ## copy 文件模板
    else:
        print("=======没有对应的子命令=======")
        printHelp()




inputProcess()