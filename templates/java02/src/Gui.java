
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;

public class Gui extends BaseFrame {


    JTabbedPane main;
    public Gui(){
        init("SISE高考预录数据信息管理系统", 900, 600);

        // 用标签面Pane 区分不同的功能模块
        main = new JTabbedPane();
        add(main, BorderLayout.CENTER);

        this.setVisible(true);
    }
    
}