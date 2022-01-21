
from . import menus

def short_desc():
    hint = "snippet 根据模板创建代码片段，放到剪贴板 \n"
    hint += "    snippet filename \n"
    return hint
# 打印提示信息
def print_help():
    hint = "至少需要1个参数。 模板文件路径 \n"
    hint += "snippet snip.txt \n"
    print(hint)

# 输入参数检查
def check_input(args):
    if(len(args) < 1): 
        print_help()
        return
    create_snippet(args[0])


def create_snippet(filename):
    file = open(filename, 'r', encoding='utf-8') 
    start = None
    snippet_type = None
    lines = []
    for line in file:
        line = line.strip()
        if line.startswith('[type]@'):
            if start is None:
                start = line
                snippet_type = line[len('[type]@'):]
                lines = []
            if line == start + "[end]":
                create_single_snippet(lines, snippet_type)
        else:
            lines.append(line)
        print(line)
    file.close()


def create_single_snippet(lines, snippet_type):
    if snippet_type == 'menu':
        menus.create(lines)