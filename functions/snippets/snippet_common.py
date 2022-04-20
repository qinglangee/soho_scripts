import clipboard as clip

from util import check_util, string_util

def snippet_result(result, unit):
    filename = unit.get_field('file')
    if filename != None:
        check_util.file_exists(filename)
        check_util.true_or_exit(unit.get_field('title') != None, "需要 title 指定更新位置")
        print('write to file:', filename)
        # 写入文件
        string_util.replace_by_flag(filename, unit.get_field('title'), result)
    else:
        print(result)
        # 内容写入剪贴板
        clip.copy(result)