import os
from .fac_java import FacJava
from .fac_cpp import FacCpp

fJava = FacJava()
fCpp = FacCpp()
# 找到源文件，分发到处理文件
def distribute(srcDir):
    files = os.listdir(srcDir)
    find_one = False
    for filename in files:
        srcPath = os.path.join(srcDir, filename)

        if(filename.startswith('zh_bean_template')):
            find_one = True

            print("template file:", filename)
            if(filename.endswith(".java.tmp")):
                fJava.create(srcPath)
            elif(filename.endswith('.cpp.tmp')):
                fCpp.create(srcPath)
            else:
                print('还没有处理的文件类型:' + srcPath)

    if not find_one:
        print("没有找到定义文件，以 zh_bean_template 开头，以类似.java.tmp 结尾")


# 打印提示信息
def print_help():
    hint = "至少需要一个参数。 生成目标路径\n"
    hint += "create . \n"
    hint += "create ./src \n"
    print(hint)

# 输入参数检查
def check_input(args):
    if(len(args) < 1): 
        print_help()
        return
    if not os.path.exists(args[0]):
        print("指定的文件或目录不存在。  请检查")
        return
    distribute(args[0])