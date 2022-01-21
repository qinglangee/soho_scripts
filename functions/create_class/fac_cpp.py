import os

from util import file_util as fu
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
        lines = fu.read_lines(os.path.join(c.createTemplateDir, 'cpp01.cpp'))
        return lines

    # 转换注释格式
    def createComment(self, comment, before="", after=""):
        # print('use fac java.createComment()')
        return '/** ' + before + comment + after + ' */'

    # 生成文件名
    def getBeanFilename(self, beanDefine):
        return os.path.join(self.fileDir, beanDefine.filename + ".cpp")