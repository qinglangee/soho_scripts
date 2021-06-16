import java.awt.Font;

import javax.swing.JLabel;

public class FontDemo extends BaseDemoJPanel{

    public FontDemo(){
        JLabel defaultLabel = createLabel(this, "Here is default content", 0, 0);
        JLabel fontLabel = createLabel(this, "Font set label", 0, 0);
        // 新建 Font
        fontLabel.setFont(new Font("",1,30));
        JLabel fontLabel2 = createLabel(this, "Font set label222", 0, 0);
        Font font = fontLabel2.getFont();
        // 修改原有的 font 
        fontLabel2.setFont(font.deriveFont(45f));
    }


    
    public static void main(String[] args) {
        new DemoTest(new FontDemo());
    }
}