# -*- coding: utf-8 -*-


# 列表内容写入文件。覆盖
def writeList(filename, list, toString=None):
    content = getListContent(list, toString)
    writeFile(filename, content)

# 列表内容转换为字符串
def getListContent(list, toString=None):
    content = ""
    for item in list:
        content = content + (toString(item) if toString != None else str(item)) + "\n"
    return content
# 列表内容写入文件。 追加 
def appendList(filename, list, toString=None):
    content = getListContent(list, toString)
    appendFile(filename, content)

# 写入文件内容。覆盖
def writeFile(filename, content):
    file_object = open(filename, 'w')
    file_object.write(content)
    file_object.close()

# 追加文件内容
def appendFile(filename, content):
    file_object = open(filename, 'a')
    file_object.write(content)
    file_object.close()

# 读取文件内容
def readFile(filename):
    with open(filename, 'r') as file:
        content = file.read()
    return content

def read_lines(filename):
    file = open(filename)
    lines = [line.strip('\n') for line in file.readlines()]
    file.close()
    return lines
