import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * 提供窗口的基本管理功能
 */
public class BaseFrame extends JFrame {
    static{
        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    
    // 设置窗口的标题和大小
    public void init(String title, int width, int height){
        this.setTitle(title);
        setSize(width, height);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//窗体居中显示
    }

    /**
     * Change content container
     * @param container
     */
    public void changeMainPanel(Container container){
        setContentPane(container);
        revalidate();
        repaint();
    }
}