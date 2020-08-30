from tkinter import *
from tkinter import ttk


class BaseWin():
    # 初始化窗口， 标题 宽度 高度
    def __init__(self, title="python", w=100, h=100):
        super().__init__()
        self.win = Tk()
        self.win.protocol('WM_DELETE_WINDOW', self.destroy) # 自定义窗口关闭事件
        
        self.init_window(title, w, h)

        self.main= ttk.Frame(self.win, padding="5 5 5 5") # padding 顺序 左上右下
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

        # self.win.resizable(0,0) #防止用户调整尺寸

    def start(self):
        self.win.mainloop()
    def destroy(self):
        self.win.destroy()

    # 组件添加到父组件的 grid 中
    # parent 父组件
    # ele 子组件
    # nextRow 是否要另起一行
    # rspan   rowspan
    # cspan   columnspan
    def grid(self, parent, ele, nextRow = False, **others):
        if(not hasattr(parent, "gridRow")):
            parent.gridRow = 0
            parent.gridCol = 0
        else:
            if(nextRow):
                parent.gridRow += 1
                parent.gridCol = 0
            else:
                parent.gridCol += 1

        ele.grid(row = parent.gridRow, column=parent.gridCol, **others)

    # 新增一个组件，并用gird布局放置
    def gridAdd(self, parent, eleType, eleParas={}, gridParas={}, rWeight=0, cWeight=0):
        ele = None
        if eleType == 'button':
            ele = Button(parent, **eleParas)
        elif eleType == 'k.button':
            ele = ttk.Button(parent, **eleParas)
        elif eleType == 'label':
            ele = Label(parent, **eleParas)
        elif eleType == 'k.label':
            ele = ttk.Label(parent, **eleParas)
        elif eleType == 'entry':
            ele = Entry(parent, **eleParas)
        elif eleType == 'k.entry':
            ele = ttk.Entry(parent, **eleParas)
        elif eleType == 'text':
            ele = Text(parent, **eleParas)
        elif eleType == 'k.text':
            ele = ttk.Text(parent, **eleParas)
        elif eleType == 'checkbutton':
            ele = Checkbutton(parent, **eleParas)
        elif eleType == 'k.checkbutton':
            ele = ttk.Checkbutton(parent, **eleParas)
        elif eleType == 'canvas':
            ele = Canvas(parent, **eleParas)
        else:
            raise Exception("Error Type:" + str(eleType))

        self.grid(parent, ele, **gridParas)
        if(rWeight != 0):
            parent.rowconfigure(parent.gridRow, weight=rWeight)
        if cWeight != 0:
            parent.columnconfigure(parent.gridCol, weight=cWeight)


        return ele
    # 在 main 中grid添加布局用的frame
    def layoutGrid(self, parent=None, rWeight=1, cWeight=1, nextRow=True, padding="3 3 3 3", others={}):
        p = parent if parent else self.main

        # gui_style = ttk.Style()
        # gui_style.configure('My.TFrame', background='#334353')
        # ele = ttk.Frame(p, padding=padding, style='My.TFrame')

        ele = ttk.Frame(p, padding=padding, **others)

        others = {'sticky':(N, W, E, S)}
        self.grid(p, ele, nextRow=nextRow, **others)
        self.main.rowconfigure(p.gridRow, weight=rWeight)	
        self.main.columnconfigure(p.gridCol, weight=cWeight)

        return ele