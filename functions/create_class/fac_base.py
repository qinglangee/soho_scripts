import re
import os

from util import file_util as fu
from .bean_define import BeanDefine, BeanField
class FacBase():
    fileDir = None
    # 流程入口
    def create(self, contentFile):
        beanDefines = self.readContent(contentFile)
        self.fileDir = os.path.dirname(contentFile)
        for beanDefine in beanDefines:
            # print(beanDefine)
            srcContent = self.createSource(beanDefine)
            self.writeSrcFile(srcContent, beanDefine)
            # print(srcContent)
            print('--------')
    
    # 读取属性内容文件
    def readContent(self, contentFile):
        res = []
        lines = fu.read_lines(contentFile)
        bean = None
        for line in lines :
            if(len(line.strip()) == 0 or line.strip().startswith("#") or line.strip().startswith("//")):
                continue   # 空行和注释不处理, 两种注释开头
            if(line.startswith("name")): # name 开头的是开始一个新的bean
                if bean != None:
                    res.append(bean)
                bean = BeanDefine()
                # print('start ', bean)
                comment = ""
                if(line.find("#") > 0):
                    comment = line[line.find("#")+1:].strip()
                parts = re.split("\s+", line)
                bean.setBeanName(parts[1])
                bean.setClassComment(comment)
            elif line.startswith('extends'): # extends 开头的是父类
                parts = re.split("\s+", line)
                bean.setParentName(parts[1])
            elif line.startswith('implements'): # extends 开头的是父类
                bean.implements = line
            elif line.startswith('import '): # import 语句
                bean.addImport(line)
            elif line.startswith('package'): # package
                bean.setPackage(line)
            elif line.startswith('toString'): # package
                parts = re.split("\s+", line)
                bean.toString = int(parts[1])
            else:  # 其余的都是字段
                field = self.parseField(line)
                bean.addField(field)
                # print(field)
        res.append(bean)
        # print('end', bean)
        # print("res[0]", res)
        return res

    # 解析字段
    def parseField(self, line):  
        comment = ""
        if line.find("#") > 0:
            comment = line[line.find("#")+1:].strip()
        parts = re.split("\s+", line)
        field = BeanField()
        field.modifier = 'private'
        field.dataType = self.parseType(parts[0])
        field.name = parts[1]
        field.comment = comment if comment != "" else parts[1]
        return field
    
    # 解析数据类型
    # **由子类具体实现**
    def parseType(self, content):
        pass

    # 创建 bean 源文件
    def createSource(self, beanDefine):
        b = beanDefine
        lines = self.readTemplateLines()
        templates = self.linesToTemplates(lines)

        # 创建所有属性
        allFieldStr = ''
        fieldTemplate = templates['field']
        for field in beanDefine.fields:
            fieldStr = fieldTemplate.replace(r'${comment}', self.createComment(field.comment))
            fieldStr = fieldStr.replace(r'${modifier}', field.modifier)
            fieldStr = fieldStr.replace(r'${type}', field.dataType)
            fieldStr = fieldStr.replace(r'${name}', field.name)
            allFieldStr += fieldStr + '\n'
        # print(allFieldStr)

        # 创建属性的getter setter 方法
        allMethods = ""
        getterTemplate = templates['getter']
        setterTemplate = templates['setter']
        for field in b.fields:
            # getterStr = self.createMethod(getterTemplate, field, 'get', '返回')
            # setterStr = self.createMethod(setterTemplate, field, 'set', '设置')
            getterStr = self.createMethod(getterTemplate, field, 'get', 'return ')
            setterStr = self.createMethod(setterTemplate, field, 'set', 'set ')
            allMethods += getterStr 
            allMethods += setterStr 

        # 创建 toString() 方法
        stringTemplate = templates['string']
        allMethods += self.createToString(stringTemplate, b)


        fileContent = templates['file']
        
        # package 和 import 就 java 会用到
        fileContent = fileContent.replace(r'${package}', beanDefine.package)
        fileContent = fileContent.replace(r'${import}', beanDefine.concatImport())

        fileContent = fileContent.replace(r'${classComment}', beanDefine.classComment)
        fileContent = fileContent.replace(r'${classname}', beanDefine.classname)
        fileContent = fileContent.replace(r'${fields}', allFieldStr)
        fileContent = fileContent.replace(r'${methods}', allMethods)
        fileContent = fileContent.replace(r'${modifier}', b.fields[0].modifier)   # c++ 有这个
        
        fileContent = fileContent.replace(r'${interfaces}', beanDefine.implements) 

        if not beanDefine.parentClassname == '':
            fileContent = fileContent.replace(r'${parent}', 'extends ' + beanDefine.parentClassname)
        else:
            fileContent = fileContent.replace(r'${parent}', '')
        

        # 读取原文件内容，如果有就保留
        fileContent = fileContent.replace(r'${originalCode}', self.readOriginalCode(beanDefine))

        return fileContent

    # 读取源文件模板
    # **由子类具体实现**
    def readTemplateLines(self):
        pass

    
    # 转换注释格式
    # **由子类具体实现**
    def createComment(self, comment):
        print('use fac base.createComment()')

    # 创建方法代码
    def createMethod(self, methodTemplate, field, methodType, commentBefore):
        name = field.name
        methodStr = methodTemplate.replace(r'${comment}', self.createComment(field.comment, before=commentBefore))
        methodStr = methodStr.replace(r'${modifier}', 'public')
        methodStr = methodStr.replace(r'${type}', field.dataType)
        methodName = methodType + name[:1].upper() + name[1:]
        methodStr = methodStr.replace(r'${methodName}', methodName)
        methodStr = methodStr.replace(r'${name}', name)
        return methodStr + '\n'

    # 创建 toString 方法
    # **由子类具体实现**
    def createToString(self, template, beanDefine):
        pass


    # 模板内容切分
    def linesToTemplates(self, lines):
        templates = {}
        templateName = None
        template = ''
        for line in lines:
            if line.startswith('```'):
                line = line.strip()
                if len(line) == 3:
                    templates[templateName] = template
                    template = ''
                elif len(line) > 3:
                    templateName = line[3:]
            else:
                template += line + "\n"
        return templates

    # 生成的内容写入文件
    def writeSrcFile(self, content, beanDefine):
        outputFile = self.getBeanFilename(beanDefine)
        print('outputfile:', outputFile)
        fu.write_file(outputFile, content)

    # **由子类具体实现, 文件名不同**
    def getBeanFilename(self, beanDefine):
        pass

    # 读取原来文件中的自写的代码
    def readOriginalCode(self, beanDefine):
        beanFileName = self.getBeanFilename(beanDefine)
        if not os.path.exists(beanFileName): # 文件不存在就返回空字符串
            return ""
        originalCode = ""
        # 内容标识符 [useful bean code start/end]

        lines = fu.read_lines(beanFileName)
        start = False
        for line in lines:
            if "useful bean code start" in line:
                start = True

            if start:
                originalCode += line + "\n"

            if "useful bean code end" in line:
                start = False
        return originalCode




