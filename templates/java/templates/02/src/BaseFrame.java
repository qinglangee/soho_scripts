import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * 提供窗口的基本管理功能
 */
public class BaseFrame extends JFrame {
    static{
        // String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Set Jframe style
        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    
    // Set Title and size
    public void init(String title, int width, int height){
        this.setTitle(title);
        setSize(width, height);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// locate to center
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