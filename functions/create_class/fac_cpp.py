import os

import file_util as fu
import config as c
from .fac_base import FacBase

class FacCpp(FacBase):

    # 解析数据类型
    def parseType(self, content):
        if content == 'i':
            return 'int'
        elif content == 's':
            return 'string'
        elif content == 'f':
            return 'float'
        elif content == 'd':
            return 'double'
        else:
            return content

    # 读取源文件模板
    def readTemplateLines(self):
        lines = fu.readLines(os.path.join(c.createTemplateDir, 'cpp01.cpp'))
        return lines

    # 转换注释格式
    def createComment(self, comment, before="", after=""):
        # print('use fac java.createComment()')
        return '/** ' + before + comment + after + ' */'

    # 生成的内容写入文件
    def writeSrcFile(self, content, beanDefine):
        fu.writeFile(beanDefine.filename + ".cpp", content)