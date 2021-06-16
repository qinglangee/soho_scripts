import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoxDemo extends JPanel{
    
    public BoxDemo(){
        Box vBox = Box.createVerticalBox();
        vBox.add(Util.label("竖排 box blue, 里面添加几个横排 box", Color.red));
        vBox.setOpaque(true);
        vBox.setBackground(Color.blue);

        Box line1 = Box.createHorizontalBox();
        line1.add(new JButton("button 01"));
        line1.add(new JButton("button 02"));
        line1.add(Util.label("button 02", Color.red));

        Box line2 = Box.createHorizontalBox();
        line2.setOpaque(true);
        line2.setBackground(Color.black);
        line2.add(Box.createHorizontalGlue());
        line2.add(new JButton("用胶水平均分配"));
        line2.add(Box.createHorizontalGlue());
        line2.add(new JButton("用胶水"));
        line2.add(Box.createHorizontalGlue());


        Box line3 = Box.createHorizontalBox();
        line3.setOpaque(true);
        line3.setBackground(Color.black);
        line3.add(Box.createHorizontalGlue());
        JButton btn31 = new JButton("用胶水平均分配,设置btn大小");
        btn31.setPreferredSize(new Dimension(200, 30));
        
        line3.add(btn31);
        
        line3.add(Box.createHorizontalGlue());
        JButton btn32 = new JButton("用胶水");
        btn32.setPreferredSize(new Dimension(200, 30));
        line3.add(btn32);
        line3.add(Box.createHorizontalGlue());


        GlueBox line4 = GlueBox.hBox();
        line4.addGlue(new JButton("btn1"));
        line4.addGlue(new JButton("btn2"));

        GlueBox line5 = GlueBox.hBox();
        line5.addGlue(new JButton("btn1"));
        line5.add(new JButton("在右边塞入固定大小的点位"));
        line5.add(Box.createHorizontalStrut(20));

        GlueBox line6 = GlueBox.hBox();
        line6.setOpaque(true);
        line6.setBackground(Color.yellow);
        line6.addGlue(new JButton("btn1"));
        line6.add(new JButton("createRigidArea 可以在横向box中把竖向撑大"));
        line6.add(Box.createRigidArea(new Dimension(30, 50)));

        vBox.add(line1);
        vBox.add(line2);
        vBox.add(line3);
        vBox.add(line4);
        vBox.add(line5);
        vBox.add(line6);
        add(vBox);
    }

    public static void main(String[] args) {
        new DemoTest(new BoxDemo());
    }
}
