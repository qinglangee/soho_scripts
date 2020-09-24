import os

import config 


# 打印提示信息
def print_help():
    hint = "至少需要两个参数。 目标路径 和 替换文本文件\n"
    hint += "rep . rep.txt\n"
    hint += "rep ./src rep.txt\n"
    print(hint)

# 输入参数检查
def check_input(args):
    if(len(args) < 1): 
        print("请输入要复制的分类！")
        return
    elif len(args) < 2:
        print("请输入要复制的[子]分类！")
        return

    # print(args[0])
    if not os.path.exists(args[0]):
        print("指定的文件或目录不存在。  请检查")
        return
    if not os.path.isfile(args[1]):
        print("替换文本文件不存在。  请检查")
        return