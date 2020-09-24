
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;

public class DemoTest extends BaseFrame {
    JComponent main;
    public DemoTest(JComponent comp){
        init("Demo 测试外壳", 900, 600);

        if(comp != null){
            main = comp;
        }else{

            // 用标签面Pane 区分不同的功能模块
            main = new JTabbedPane();
        }
        add(main, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new DemoTest(null);
    }
}

