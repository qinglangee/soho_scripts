
# 不足长度的字符串补充指定的字符
# text 源字符串
# length 长度
# fill 补充用的字符
def fill_head(text, length, fill=' '):
    if(len(text) < length):
        for i in range(length- len(text)):
            text = fill + text
    return text


def format_str(str, *args):
    return str.format(*args)