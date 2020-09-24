
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;

public class ListenerDemo extends JPanel{

    public JButton login = new JButton("demo");
    JRadioButton radioBtn = new JRadioButton("click me");

    public void bindEvent(){
        
        login.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // action code
                System.out.println("click login");
            }
        });

        radioBtn.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent changEvent) {
                AbstractButton aButton = (AbstractButton)changEvent.getSource();
                ButtonModel aModel = aButton.getModel();
                boolean armed = aModel.isArmed();
                boolean pressed = aModel.isPressed();
                boolean selected = aModel.isSelected();
                System.out.println("Changed: " + armed + "/" + pressed + "/" +
                  selected);
            }
        });
    }

    public ListenerDemo(){
        add(login);
        add(radioBtn);
        bindEvent();
    }

    public static void main(String[] args) {
        new DemoTest(new ListenerDemo());
    }
}
