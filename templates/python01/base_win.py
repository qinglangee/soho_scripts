from tkinter import *

class BaseWin():
    # 初始化窗口， 标题 宽度 高度
    def __init__(self, title="python", w=100, h=100):
        super().__init__()
        self.win = Tk()
        self.init_window(title, w, h)

    def init_window(self, title, w, h):
        self.win.title(title)
        # 获取屏幕分辨率
        screenWidth = self.win.winfo_screenwidth()
        screenHeight = self.win.winfo_screenheight()
        
        x = int((screenWidth - w) / 2)
        y = int((screenHeight - h) / 2)
        # 设置窗口初始位置在屏幕居中
        self.win.geometry("%sx%s+%s+%s" % (w, h, x, y))

        self.win.resizable(0,0) #防止用户调整尺寸

    def start(self):
        self.win.mainloop()
    def destory(self):
        self.win.destroy()