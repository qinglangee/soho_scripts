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
        screen_width = self.win.winfo_screenwidth()
        screen_height = self.win.winfo_screenheight()
        
        x = int((screen_width - w) / 2)
        y = int((screen_height - h) / 2)
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
    # next_row 是否要另起一行
    # rowspan   rowspan
    # columnspan   columnspan
    def grid(self, parent, ele, next_row = False, **others):
        if(not hasattr(parent, "gridRow")):
            parent.gridRow = 0
            parent.gridCol = 0
        else:
            if(next_row):
                parent.gridRow += 1
                parent.gridCol = 0
            else:
                parent.gridCol += 1

        ele.grid(row = parent.gridRow, column=parent.gridCol, **others)

    # 新增一个组件，并用gird布局放置
    # parent 父组件
    # ele_type 要创建组件的类型
    # ele_paras  传递给组件的参数
    # grid_paras 传递给 self.grid() 方法的参数
    # r_weight  所占行的权重
    # c_weight  所占列的权重
    def grid_add(self, parent, ele_type, ele_paras={}, grid_paras={}, r_weight=0, c_weight=0):
        ele = None
        if ele_type == 'button':
            ele = Button(parent, **ele_paras)
        elif ele_type == 'k.button':
            ele = ttk.Button(parent, **ele_paras)
        elif ele_type == 'label':
            ele = Label(parent, **ele_paras)
        elif ele_type == 'k.label':
            ele = ttk.Label(parent, **ele_paras)
        elif ele_type == 'entry':
            ele = Entry(parent, **ele_paras)
        elif ele_type == 'k.entry':
            ele = ttk.Entry(parent, **ele_paras)
        elif ele_type == 'text':
            ele = Text(parent, **ele_paras)
        elif ele_type == 'k.text':
            ele = ttk.Text(parent, **ele_paras)
        elif ele_type == 'checkbutton':
            ele = Checkbutton(parent, **ele_paras)
        elif ele_type == 'k.checkbutton':
            ele = ttk.Checkbutton(parent, **ele_paras)
        elif ele_type == 'combobox':
            ele = ttk.Combobox(parent, **ele_paras)
        elif ele_type == 'k.combobox':
            ele = ttk.Combobox(parent, **ele_paras)
        elif ele_type == 'frame':
            ele = Frame(parent, **ele_paras)
        elif ele_type == 'k.frame':
            ele = ttk.Frame(parent, **ele_paras)
        elif ele_type == 'canvas':
            ele = Canvas(parent, **ele_paras)
        else:
            raise Exception("Error Type:" + str(ele_type))

        self.grid(parent, ele, **grid_paras)
        if(r_weight != 0):
            parent.rowconfigure(parent.gridRow, weight=r_weight)
        if c_weight != 0:
            parent.columnconfigure(parent.gridCol, weight=c_weight)


        return ele
    # 在 main 中grid添加布局用的frame
    def layout_grid(self, parent=None, r_weight=1, c_weight=1, next_row=True, padding="3 3 3 3", others={}):
        p = parent if parent else self.main

        # gui_style = ttk.Style()
        # gui_style.configure('My.TFrame', background='#334353')
        # ele = ttk.Frame(p, padding=padding, style='My.TFrame')

        ele = ttk.Frame(p, padding=padding, **others)

        others = {'sticky':(N, W, E, S)}
        self.grid(p, ele, next_row=next_row, **others)
        p.rowconfigure(p.gridRow, weight=r_weight)	
        p.columnconfigure(p.gridCol, weight=c_weight)

        return ele