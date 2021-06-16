import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Util {
    public static JLabel label(String name){
        return label(name, null);
    }

    // 设置标签内容和背景色
    public static JLabel label(String name, Color color){
        JLabel label = new JLabel(name);
        if(color != null){
            label.setOpaque(true);
            label.setBackground(color);
        }
        return label;
    }

    // 设置面板背景色
    public static JPanel panel(Color color){
        JPanel panel = new JPanel();
        if(color != null){
            panel.setOpaque(true);
            panel.setBackground(color);
        }
        return panel;
    }
    
}
