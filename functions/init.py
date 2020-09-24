import shutil
import os
import sys

import config as c
def print_help():
    hint = "至少需要两个参数。 目标路径 和 源码类型\n"
    hint += "init . cpp\n"
    hint += "init . cpp 02\n"
    hint += "init . cpp 99  (复制工具文件)\n"
    print(hint)

# 把源目录的文件全部复制到目标目录
def copy_sub_dir(dest, src_type, num='01'):
    root_path = os.path.join(c.templateDir, src_type, "templates", num)
    files = os.listdir(root_path)
    for filename in files:
        #获取目录或者文件的路径
        src_path = os.path.join(root_path,filename)
        dest_path = os.path.join(dest, filename)
        # 只做初始化，已有就退出程序
        if os.path.exists(dest_path):
            print("path:", dest_path)
            print('目标路径已存在，停止复制！')
            break
        #判断该路径为文件还是路径
        if os.path.isdir(src_path):
            print("cp  dir:", filename)
            shutil.copytree(src_path, dest_path)
        else:
            print("cp file:" , filename)
            shutil.copyfile(src_path, dest_path)

    if src_type == 'java':
        copy_util_files(dest, src_type)

    copy_gitignore(dest)

# 复制相关工具类
def copy_util_files(dest, src_type):
    root_path = os.path.join(c.templateDir, src_type, "util")
    files = os.listdir(root_path)
    dir_name_src = os.path.join(dest, "src")
    for filename in files:
        src_path = os.path.join(root_path,filename)
        dest_path = os.path.join(dest, filename)
        if(os.path.exists(dir_name_src)):
            dest_path = os.path.join(dir_name_src, filename)
        print("cp file:" , filename)
        shutil.copyfile(src_path, dest_path)

# 复制 git 的配置文件
def copy_gitignore(dest):
    root_path = os.path.join(c.templateDir, 'git')
    files = os.listdir(root_path)
    for filename in files:
        src_path = os.path.join(root_path,filename)
        dest_path = os.path.join(dest, filename)
        print("cp file:" , filename)
        shutil.copyfile(src_path, dest_path)

# 对输入参数进行基本的检查
def check_input(args):
    # print(len(args))
    if(len(args) < 2): 
        print_help()
        return
    dest = args[0]
    src_type = args[1]

    num = "01"
    if(len(args) > 2):
        num = args[2]
    
    if num == "99":
        copy_util_files(dest, src_type)
    else:
        copy_sub_dir(dest, src_type, num)

# check_input()