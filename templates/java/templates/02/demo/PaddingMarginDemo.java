import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.*;

public class PaddingMarginDemo extends JPanel{

    public PaddingMarginDemo(){
        paddingDemo();
        panelPaddingDemo();
    }

    private void paddingDemo(){
        JPanel panel = Util.panel(Color.RED);
        JButton b1 = new JButton("default");
        JButton b2 = new JButton("用Insets实现padding");
        b2.setMargin(new Insets(10, 20, 40, 80)); // 上 左 下 右
        
        JButton b3 = new JButton("用border实现padding");
        b3.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 40));


        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        add(panel);

    }

    private void panelPaddingDemo(){
        JPanel panel = Util.panel(Color.green);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 40, 80));
        JButton b1 = new JButton("panel set border 10 20 40 80");
        panel.add(b1);
        add(panel);
    }
    public static void main(String[] args) {
        new DemoTest(new PaddingMarginDemo());
    }
    
}
