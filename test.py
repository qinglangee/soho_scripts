
from functions.create_class.bean_define import BeanField

import re
print(re.match('www', 'www.runoob.com').span())  # 在起始位置匹配 用span()
print(re.match('www', 'www.runoob.com'))  # 在起始位置匹配 用span()
print(re.match('com', 'www.runoob.com'))         # 不在起始位置匹配, 返回 None


m = re.match('www', 'www.runoob.com')
print(m.span())  # 匹配位置
print("-----")
mf = re.fullmatch('www.runoob.com', 'www.runoob.com')
print(mf)
print(mf.span())
print(mf.expand('mm'))

print("============")
print(re.split(r'\W+', 'Words, words, words.'))
print(re.split(r'(\W+)', 'Words, words, words.'))
print("============")
print(re.findall(r'(\w+)=(\d+)', 'set width=20 and '))