
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.util.*;
public class BorderDemo extends JPanel{

    public BorderDemo(){
        setBorder();
    }

    public void addButton(String name, Border border){
        JButton btn = new JButton(name);
        btn.setBorder(border);
        btn.setPreferredSize(new Dimension(200, 100));
        add(btn);
    }

    public void setBorder(){

        JPanel jpanel = new JPanel();
    
        // jpanel.setBorder(BorderFactory.你需要的样式);
        
        addButton("线边框", BorderFactory.createLineBorder(Color.red, 3));

        
        addButton("蚀刻边框", BorderFactory.createEtchedBorder());                 //
        
        addButton("斜面边框(凸)", BorderFactory.createRaisedBevelBorder());            //
        
        addButton("斜面边框(凹)", BorderFactory.createLoweredBevelBorder());           //斜面边框(凹)
        
        addButton("标题", BorderFactory.createTitledBorder("标题"));          //标题边框左
        
        Border b = BorderFactory.createTitledBorder("标题");          //标题边框右
        // setTitleJustification(TitledBorder.RIGHT);
        
        addButton("花色边框", BorderFactory.createMatteBorder(1, 5, 1, 1, Color.yellow)); //花色边框
        
        Border b1 = BorderFactory.createLineBorder(Color.blue, 2);  //组合边框
        Border b2 = BorderFactory.createEtchedBorder();
        addButton("组合边框", BorderFactory.createCompoundBorder(b1, b2));
    }

    public static void main(String[] args) {
        new DemoTest(new BorderDemo());
    }
}
