from collections import OrderedDict
import clipboard

names = OrderedDict({
    'git':[
        'git archive --format zip --output /path_out.zip master',
        'git update-index --assume-unchanged filename',
        'git update-index --no-assume-unchanged filename',
        'git config user.name "name"',
        'git config user.email "email"',
        "git config --global http.proxy 'socks5://127.0.0.1:20808' ",
        "git config --global https.proxy 'socks5://127.0.0.1:20808'",
        "git config --global --unset http.proxy",
        "git config --global --unset https.proxy"
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
    'gradle':[
        'gradle test',
        'gradle checkstyleMain'
    ],
    'python':[
        'python -m venv  myvenv',
        'myvenv/Scripts/activate.ps1',
        'pip install -r requirements.txt',
        'd:\document\python_env\myvenv\Scripts\Activate.ps1',
        'python -m http.server'
    ],
    # bash 可能会比较多，可以多分几组
    'bash lslibs':[],
    'lslibs':[
        'dir=target/dependency/;aa="";for f in `ls $dir`;do aa="$aa;$dir$f"; done; echo $aa'
    ],
    # java
    'java uml': [],
    'uml':['java -cp "classes;urm-core.jar;target\dependency\*"  com.iluwatar.urm.DomainMapperCli -p "com.pkg,com.aaa" -f class.puml -i "com.abc,com.def"']

})


def short_desc():
    hint = "tishi 提示一些命令用法 \n"
    hint += "    tishi git 1\n"
    hint += "    tishi xcopy \n"
    return hint
def print_help():
    hint = "至少需要一个参数。 命令名字 <复制index>\n"
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
    copy_index = 0  # 指定复制哪一个命令
    if len(args) > 1:
        copy_index = int(args[1])  # 第2个参数作为复制的索引
    show_cmd(name, copy_index)

def show_cmd(name, copy_index=0):
    if name in names:
        items = names[name]
        print(name,":")
        index = 0
        for item in items:
            print("\t", str(index) + '.', item)
            if copy_index==index:
                clipboard.copy(item)
            index += 1
    else:
        print_help()
    