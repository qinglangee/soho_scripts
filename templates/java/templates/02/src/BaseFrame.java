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

    public BaseFrame(){
    }
    
    public BaseFrame(String title){
        super(title);
    }

    
    // Set Title and size
    public void init(String title, int width, int height){
        this.setTitle(title);
        setSize(width, height);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// locate to center
    }

    // Set location to center when multiple screens exist
    private void setLocationCenter() {
        try {

            // 获取当前的图形环境对象
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            // 获取所有的屏幕设备对象
            GraphicsDevice[] gds = ge.getScreenDevices();
            // 选择第一个屏幕设备对象
            GraphicsDevice gd = gds[0];
            // 获取该屏幕设备的默认配置对象
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            // 获取该屏幕设备的边界矩形对象
            Rectangle rect = gc.getBounds();
            // 计算该矩形对象的中心点
            int x = rect.x + rect.width / 2 - getWidth() / 2;
            int y = rect.y + rect.height / 2 - getHeight() / 2;
            // 设置程序窗口的位置为该中心点
            setLocation(x, y);
        } catch (Exception e) {
            e.printStackTrace();
        }
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