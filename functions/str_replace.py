import os
import re

from util import file_util as fu

G_replaceTxt = ""


def short_desc():
    hint = "rep 内容替换: 参数 1 替换的目录或文件 2 替换内容文件  \n"
    hint += "    把参数1中文件内容按参数2文件中的替换规则替换掉\n"
    hint += "    参数2文件中每行两个字符串以空格分隔开\n"
    hint += "    rep ./src rep.txt\n"
    return hint
# 打印提示信息
def print_help():
    hint = "至少需要两个参数。 目标路径 和 替换文本文件\n"
    hint += "rep . rep.txt\n"
    hint += "rep ./src rep.txt\n"
    print(hint)
    
# 
# 替换程序处理入口
def replace(srcFiles, replaceTxt):
    # 设置替换文件名，遇到不替换
    global G_replaceTxt
    G_replaceTxt = os.path.basename(replaceTxt).strip()
    # print("repTxt ", replaceTxt, "- -", G_replaceTxt,"-")
    repList = []

    # 切分函数。 替换文件中的内容就是以空格分隔的两个字符串一行
    def splitLine(line):
        parts= re.split("\s+", line)
        # print("---", parts)
        if len(parts) == 2:
            return parts
        else:
            return None

    repList = fu.read_and_process(replaceTxt, splitLine)
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
    content = fu.read_file(filename)
    newFilename = filename
    for rep in repList:
        content = content.replace(rep[0], rep[1])
        # newFilename = newFilename.replace(rep[0], rep[1])
    fu.write_file(newFilename, content)
    # if newFilename != filename:
    #     os.remove(filename)


# 输入参数检查
def check_input(args):
    if(len(args) < 2): 
        print_help()
        return
    if not os.path.exists(args[0]):
        print("指定的文件或目录不存在。  请检查")
        return
    if not os.path.isfile(args[1]):
        print("替换文本文件不存在。  请检查")
        return
    replace(args[0], args[1])