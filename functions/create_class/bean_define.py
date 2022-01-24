# 代表一个 bean 的定义内容
class BeanDefine():
    def __init__(self):
        # package 和 importItems 只有 java 会用到
        self.package = ""
        self.importItems = []

        self.filename = "defaultFileName"
        self.parentClassname = ""
        self.classname = "defaultBeanName"
        self.classComment = ""
        self.fields = []
        self.originalCode = "" # 需要替换的原文件代码，默认为空
        self.toString = 0 # 设置 toString() 方法的形式
        self.implements = ""

    # 设置文件名和类名
    def setBeanName(self, name):
        self.filename = name
        parts = name.split('_')
        self.classname = ''
        for part in parts:
            self.classname += part[0].upper() + part[1:]
    
    # 设置父类名
    def setParentName(self, name):
        parts = name.split('_')
        self.parentClassname = ''
        for part in parts:
            self.parentClassname += part[0].upper() + part[1:]
    
    # 设置类注释
    def setClassComment(self, comment):
        self.classComment = comment

    # 添加一个字段
    def addField(self, modifier, type, name, comment):
        field = BeanField(modifier, type, name, comment)
        self.fields.append(field)
    
    def addField(self, field):
        self.fields.append(field)

    # 设置 package
    def setPackage(self, package):
        self.package = package
    # 添加一个import
    def addImport(self, importItem):
        self.importItems.append(importItem)

    # 组合 java 的 import
    def concatImport(self):
        importStr = ""
        for importItem in self.importItems:
            importStr += importItem + "\n"
        return importStr


    def __str__(self):
        res = self.classname
        for field in self.fields:
            res += "\n" + str(field)
        return res


# bean 的一个字段
class BeanField():
    modifier = ""
    dataType = ""
    name = ""
    comment = ""

    def __init__(self, *args):
        argNum = len(args)
        if argNum == 0:
            pass
        elif argNum == 4:
            self.init(args[0], args[1], args[2], args[3])
        else:
            self.init()
    def init(self, modifier, t, name, comment=""):
        self.modifier = modifier
        self.dataType = t
        self.name = name
        self.comment = comment

    def __str__(self):
        return ("%s %s %s %s" % (self.modifier, self.dataType, self.name , self.comment))