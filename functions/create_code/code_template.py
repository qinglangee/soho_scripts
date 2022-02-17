import os
from util import dir_util

from . import html_code
from . import sql_code

def short_desc():
    hint = "code 根据模板创建文件, 设置都在模板文件里 \n"
    hint += "    code . \n"
    return hint
# 打印提示信息
def print_help():
    hint = "至少需要1个参数。 模板文件路径 \n"
    hint += "code . \n"
    print(hint)

# 输入参数检查
def check_input(args):
    if(len(args) < 1): 
        print_help()
        return
    execute(args[0])

# 读取设置文件，调用不同的子功能进行生成文件
def execute(src):
    # 在 .hint目录中查找 _temp.txt 结尾的文件
    files = dir_util.get_files(os.path.join(src, ".hint"), "_temp.txt")
    if len(files) == 0:
        print("没有模板定义文件")
        return
    for filename in files:
        if "html_temp.txt" in filename:
            html_code.process(src, filename)
        elif "sql_temp.txt" in filename:
            sql_code.process(src, filename)
