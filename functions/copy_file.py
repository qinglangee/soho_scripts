import os
import shutil

import config 
import dir_util


# 打印提示信息
def print_help():
    hint = "至少需要两个参数。 目标路径 和 替换文本文件\n"
    hint += "rep . rep.txt\n"
    hint += "rep ./src rep.txt\n"
    print(hint)

# 输入参数检查
# args[0] 要写入的目录
# args[1] 分类
# args[2] 子分类
def check_input(args):
    if(len(args) < 2): 
        print("请输入要复制的分类！")
        dir_util.list_dir(config.template_dir)
        return
    elif len(args) < 3:
        print("请输入要复制的[子]分类！")
        dir_util.list_dir(os.path.join(config.template_dir, args[1]))
        return

    src_dir = os.path.join(config.template_dir, args[1], args[2])
    if not os.path.exists(src_dir):
        print("指定的文件或目录不存在。  请检查")
        return
    dest_dir = os.path.join(args[0], "zh_tmp", args[2])
    if(os.path.isfile(src_dir)):
        shutil.copyfile(src_dir, dest_dir)
    else:
        shutil.copytree(src_dir, dest_dir)