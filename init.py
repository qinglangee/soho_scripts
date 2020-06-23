import shutil
import os
import sys

import config as c
def printHelp():
    hint = "至少需要两个参数。 目标路径 和 源码类型\n"
    hint += "init . c++\n"
    hint += "init . c++ 02\n"
    print(hint)

# 把源目录的文件全部复制到目标目录
def copySubDir(dest, srcType, num='01'):
    rootPath = c.templateDir + srcType + num
    files = os.listdir(rootPath)
    for filename in files:
        #获取目录或者文件的路径
        srcPath = os.path.join(rootPath,filename)
        destPath = os.path.join(dest, filename)
        #判断该路径为文件还是路径
        if os.path.isdir(srcPath):
            print("cp  dir:", filename)
            shutil.copytree(srcPath, destPath)
        else:
            print("cp file:" , filename)
            shutil.copyfile(srcPath, destPath)

# 对输入参数进行基本的检查
def checkInput(args):
    # print(len(args))
    if(len(args) < 2): 
        printHelp()
        return
    dest = args[0]
    srcType = args[1]

    num = "01"
    if(len(args) > 2):
        num = args[2]
    
    copySubDir(dest, srcType, num)

# checkInput()