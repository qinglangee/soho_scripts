# soho_scripts
some scripts

## 脚本介绍
soho.bat  入口，在命令行可以调用。 不作额外处理，直接把参数都发给 python 脚本   

input_process.py 为各个子命令进行路由和分配参数

init.py  初始化目录的功能，根据参数，把相应的模板目录结构复制到目的地。  

str_replace.py  目录内容字符串替换，给已有项目改头换面用。  

create_class.py  根据配置文件生成 class 文件。 java c++ 等，别的以后再添加