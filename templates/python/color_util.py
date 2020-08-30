


 
# RGB格式颜色转换为16进制颜色格式
# 输入 rgb 为元组  (r,g,b)
def rgbToHex(rgb):
    # RGB = rgb.split(',')            # 将RGB格式划分开来
    color = '#'
    for i in rgb:
        num = int(i)
        # 将R、G、B分别转化为16进制拼接转换并大写  hex() 函数用于将10进制整数转换成16进制，以字符串形式表示
        color += str(hex(num))[-2:].replace('x', '0').upper()
    # print(color)
    return color
 
 
# 16进制颜色格式颜色转换为RGB格式
# 输入 hex 为6位颜色值   #25A2E3
def hexToRgb(hex):
    r = int(hex[1:3],16)
    g = int(hex[3:5],16)
    b = int(hex[5:7], 16)
    rgb = (r,g,b)
    # print(rgb)
    return rgb
# rgb 各乘上一个数
def multy(rgb, n):
    r = int(rgb[0]*n)
    g = int(rgb[1]*n)
    b = int(rgb[2]*n)
    return (r,g,b)