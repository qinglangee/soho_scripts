import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BaseDemoJPanel extends JPanel{
    
    public JLabel createLabel(JComponent parent, String title, int width, int height){
        JLabel label = new JLabel(title);
        
        if(parent != null){
            parent.add(label);
        }

        if(width > 0){
            label.setPreferredSize(new Dimension(width, height));
        }

        return label;
    }
    public JButton createButton(JComponent parent, String title, int width, int height){
        JButton button = new JButton(title);
        
        if(parent != null){
            parent.add(button);
        }

        if(width > 0){
            button.setPreferredSize(new Dimension(width, height));
        }

        return button;
    }
}
