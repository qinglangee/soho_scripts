
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;

public class Gui extends BaseFrame {


    public JTabbedPane main;
    public Gui(){
        init("Gui System", 900, 600);

        // 用标签面Pane 区分不同的功能模块
        main = new JTabbedPane();
        add(main, BorderLayout.CENTER);

        
    }
    
}