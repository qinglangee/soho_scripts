# -*- coding: utf-8 -*-


# 列表内容写入文件。覆盖
def write_list(filename, list, toString=None):
    content = getListContent(list, toString)
    writeFile(filename, content)

# 列表内容转换为字符串
def get_list_content(list, toString=None):
    content = ""
    for item in list:
        content = content + (toString(item) if toString != None else str(item)) + "\n"
    return content
# 列表内容写入文件。 追加 
def append_list(filename, list, toString=None):
    content = getListContent(list, toString)
    appendFile(filename, content)

# 写入文件内容。覆盖
def write_file(filename, content):
    file_object = open(filename, 'w')
    file_object.write(content)
    file_object.close()

# 追加文件内容
def append_file(filename, content):
    file_object = open(filename, 'a')
    file_object.write(content)
    file_object.close()

# 读取文件内容
def read_file(filename):
    with open(filename, 'r') as file:
        content = file.read()
    return content

def read_lines(filename):
    file = open(filename)
    lines = [line.strip('\n') for line in file.readlines()]
    file.close()
    return lines
