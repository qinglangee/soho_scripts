import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SpringPanelDemo  extends JPanel{

    public SpringPanelDemo(){
        setLayout(new GridLayout (2,3));

        SpringPanel p1 = new SpringPanel();
        p1.setBackground(Color.GREEN);
        p1.add(new JButton("label001"));
        p1.add(new JButton("label002"));
        p1.add(new JButton("label003"));

        SpringPanel p2 = new SpringPanel(1);
        p2.setBackground(Color.RED);
        p2.add(new JButton("label201"));
        p2.add(new JButton("label202"));
        p2.add(new JButton("label203"));

        SpringPanel p3 = new SpringPanel(2);
        p3.setFill(true);
        p3.setBackground(Color.YELLOW);
        p3.add(new JButton("label301"));
        p3.add(new JButton("label302"));
        p3.add(new JButton("label303"));

        SpringPanel p4 = new SpringPanel(3);
        p4.setFill(true);
        p4.setBackground(Color.WHITE);
        p4.add(new JButton("label401"));
        p4.add(new JButton("label402"));
        p4.add(new JButton("label403"));

        add(p1);
        add(p2);
        add(p3);
        add(p4);
    }
    

    public static void main(String[] args) {
        // new DemoTest(new SpringPanelDemo());

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        SpringPanel panel = new SpringPanel();
        panel.setBackground(Color.yellow);
        panel.add(new JButton("1212"));
        panel.add(new JButton("1213"));
        frame.add(panel);
        
        SpringPanel leftPanel = new SpringPanel(0, 10);
        
        leftPanel.setBackground(Color.BLUE);
        panel.add(leftPanel);
        leftPanel.add(new JButton("left 001"));
        leftPanel.add(new JLabel("left 002"));
        leftPanel.add(new JLabel("left 003"));
        // leftPanel.setPreferredSize(new Dimension(200, 200));
        leftPanel.calcSize();
        
        // panel.finishLast();

        panel.add(new SpringPanelDemo());

        frame.setVisible(true);
    }
}
