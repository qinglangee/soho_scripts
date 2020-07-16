from tkinter import *
from tkinter import ttk
import tkinter
# from PIL import Image, ImageTk
# import PIL

from base_win import BaseWin
from gui_util import *
import config as c
import file_util as fu

class AppWin(BaseWin):
    def __init__(self):
        super().__init__(c.winTitle, c.winWidth, c.winHeight)

        self.initFrame()
    
    def initFrame(self):
        self.leftFrame = ttk.Frame(self.main)
        self.leftFrame.pack()

        btn = ttk.Button(self.leftFrame, text="click", command=self.aap)
        btn.pack()
    def aap(self):
        showErr("abcd")


if __name__ == "__main__":
    app = AppWin()
    app.start()
    