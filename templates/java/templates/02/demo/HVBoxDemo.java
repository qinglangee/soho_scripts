import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HVBoxDemo extends JPanel{
    

    public HVBoxDemo(){
        setLayout(new BorderLayout());
        JPanel hbox = new JPanel();

        
        VBox left = new VBox(10);
        left.setPadding(10);
        left.box.setOpaque(true);
        left.box.setBackground(Color.GREEN);
        left.add(new JButton("left 01"));
        JButton b2 = new JButton("left 02");
        left.add(b2);
        left.add(new JButton("left 03"));

        add(left.box, BorderLayout.WEST);

        HBox center = new HBox(10);
        
        center.box.setOpaque(true);
        center.box.setBackground(Color.YELLOW);
        center.setUseGlue(true);
        center.add(new JButton("center 01"));
        center.add(new JButton("center 02"));
        center.add(new JButton("center 03"));

        add(center.box, BorderLayout.CENTER);
        // add(hbox);
    }


    public static void main(String[] args) {
        new DemoTest(new HVBoxDemo());
    }
}
