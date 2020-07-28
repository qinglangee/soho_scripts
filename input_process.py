import sys

from functions import init
from functions import str_replace
from functions.create_class import factory


def printHelp():
    hint = "请输入正确的参数。soho subcmd par1 par2 ...\n"
    hint += "subcmd 子命令有下列选项：\n"
    hint += "init 复制模板: 参数 1 类型 2 序号      init . c++ 02\n"
    hint += "rep 内容替换: 参数 1 替换的目录或文件 2 替换内容文件   rep ./src rep.txt\n"
    hint += "create 内容替换: 参数 1 要扫描的目录 \n"
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
        init.checkInput(args[2:])  ## 复制模板文件
    elif subcmd == "rep":
        str_replace.checkInput(args[2:])  ## 文件内容替换
    elif subcmd == "create":
        factory.checkInput(args[2:])  ## 文件内容替换
    else:
        printHelp()




inputProcess()