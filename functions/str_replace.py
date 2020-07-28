import os
import re

import file_util as fu

G_replaceTxt = ""
# 
# 替换程序处理入口
def replace(srcFiles, replaceTxt):
    # 设置替换文件名，遇到不替换
    global G_replaceTxt
    G_replaceTxt = os.path.basename(replaceTxt).strip()
    # print("repTxt ", replaceTxt, "- -", G_replaceTxt,"-")
    repList = []
    def splitLine(line):
        parts= re.split("\s+", line)
        # print("---", parts)
        if len(parts) == 2:
            return parts
        else:
            return None

    repList = fu.readAndProcess(replaceTxt, splitLine)
    print(repList)

    if os.path.isfile(srcFiles):
        replaceFile(srcFiles, repList)
    else:
        replaceDir(srcFiles, repList)
# 处理替换目录中的内容
def replaceDir(dirPath, repList):
    files = os.listdir(dirPath)
    for filename in files:
        srcPath = os.path.join(dirPath,filename)
        if os.path.isdir(srcPath):
            replaceDir(srcPath, repList)
        else:
            replaceFile(srcPath, repList)
# 替换文件中的文本内容
def replaceFile(filename, repList):
    # 替换文件本身不处理
    # print("-",os.path.basename(filename),"-,-",G_replaceTxt,"-")
    if os.path.basename(filename) == G_replaceTxt:
        return
    content = fu.readFile(filename)
    newFilename = filename
    for rep in repList:
        content = content.replace(rep[0], rep[1])
        # newFilename = newFilename.replace(rep[0], rep[1])
    fu.writeFile(newFilename, content)
    # if newFilename != filename:
    #     os.remove(filename)


# 打印提示信息
def printHelp():
    hint = "至少需要两个参数。 目标路径 和 替换文本文件\n"
    hint += "rep . rep.txt\n"
    hint += "rep ./src rep.txt\n"
    print(hint)

# 输入参数检查
def checkInput(args):
    if(len(args) < 2): 
        printHelp()
        return
    if not os.path.exists(args[0]):
        print("指定的文件或目录不存在。  请检查")
        return
    if not os.path.isfile(args[1]):
        print("替换文本文件不存在。  请检查")
        return
    replace(args[0], args[1])