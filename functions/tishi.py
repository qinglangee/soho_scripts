
names = {
    'git':[
        'git archive --format zip --output /path_out.zip master',
        'git update-index --assume-unchanged filename',
        'git update-index --no-assume-unchanged filename',
    ],
    'xcopy':['xcopy /S /Y src dest    # /S 递归复制所有目录  /Y 确认覆盖旧文件'],
    'hash':[
        'Get-FileHash ".\\2021-04-18 21-18-28.mkv" -Algorithm md5',
        'Get-FileHash ".\\2021-04-18 21-18-28.mkv" -Algorithm sha256'
    ],
    'php':{
        'php -S localhost:8000',
        'php -S localhost:8000 -t foo/   指定根目录',
    }
}


def short_desc():
    hint = "tishi 提示一些命令用法 \n"
    hint += "    tishi git \n"
    hint += "    tishi xcopy \n"
    return hint
def print_help():
    hint = "至少需要一个参数。 命令名字\n"
    hint += "tishi git\n"
    hint += "可以查看如下命令:\n"
    print(hint)
    

    name_list = sorted([name for name in names.keys()])
    i = 0
    for name in name_list:
        i += 1
        print(name, " ")
        if i%5 == 0:
            print("")

# 对输入参数进行基本的检查
def check_input(args):
    if(len(args) < 1): 
        print_help()
        return
    name = args[0]
    show_cmd(name)

def show_cmd(name):
    if name in names:
        items = names[name]
        print(name,":")
        for item in items:
            print("\t", item)
    else:
        print_help()
    