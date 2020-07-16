from tkinter import *
from tkinter import ttk


class BaseWin():
    # 初始化窗口， 标题 宽度 高度
    def __init__(self, title="python", w=100, h=100):
        super().__init__()
        self.win = Tk()
        self.init_window(title, w, h)

        self.main= ttk.Frame(self.win, padding="3 3 12 12")
        self.main.grid(column=0, row=0, sticky=(N, W, E, S))
        self.win.columnconfigure(0, weight=1)
        self.win.rowconfigure(0, weight=1)	

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
