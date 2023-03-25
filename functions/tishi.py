from collections import OrderedDict

names = OrderedDict({
    'git':[
        'git archive --format zip --output /path_out.zip master',
        'git update-index --assume-unchanged filename',
        'git update-index --no-assume-unchanged filename',
    ],
    'php':{
        'php -S localhost:8000',
        'php -S localhost:8000 -t foo/   指定根目录',
    },
    # 空格分隔的作为一个分类，包含多个子命令, 方便放在一起显示。 子命令后面跟随就可以了
    'wins xcopy mklink':[],
    'xcopy':['xcopy /S /Y src dest    # /S 递归复制所有目录  /Y 确认覆盖旧文件'],
    'mklink':[
        'mklink 符号链接文件  目标文件',
        'mklink /d  符号链接目录  目标目录'
    ],
    'hash':[
        'Get-FileHash ".\\2021-04-18 21-18-28.mkv" -Algorithm md5',
        'Get-FileHash ".\\2021-04-18 21-18-28.mkv" -Algorithm sha256'
    ],
})


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
    
    sub_cmds = []
    for name in names:
        # 检查一下， 子命令就一起显示了
        if len(name.split(' ')) > 1:
            sub_cmds = sub_cmds + name.split(' ')
        if name not in sub_cmds:
            print(name, " ")

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
    