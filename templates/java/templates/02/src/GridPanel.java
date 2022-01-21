
import java.awt.*;

import javax.swing.*;
import java.util.*;

public class GridPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    
    private int gridY = 0; // new component's row
    
    protected GridBagLayout layout; 
    // default insets of components
    protected Insets defaultInset = new Insets(5, 5, 5, 5); 

    public GridPanel(){
        layout = new GridBagLayout();
        setLayout(layout);
    }

    // add to current row
    public void addToRow(JComponent ... comps){
        for(JComponent comp : comps){
            addToRow(comp, gridY);
        }
    }
    // add component to next row
    public void addToNewRow(JComponent comp){
        gridY++;
        addToRow(comp, gridY);
    }

    // add component to assigned row
    public void addToRow(JComponent comp, int y){
        addToRow(comp, y, null);
    }
    // add component to assigned row
    public void addToRow(JComponent comp, int gridY, GridBagConstraints c){
        GridBagConstraints constraints= c!=null?c:new GridBagConstraints();
        constraints.gridy=gridY;
        addComp(constraints, comp);
    }

    public void addComp(JComponent comp, int x, int y){
        addComp(comp, x, y, null);
    }
    
    public void addComp(JComponent comp, int x, int y, GridBagConstraints c){
        GridBagConstraints constraints= c!=null ? c : new GridBagConstraints();
        constraints.gridx=x;
        constraints.gridy=y;
        addComp(constraints, comp);
    }

    // common method for add component
    public void addComp(GridBagConstraints constraints, JComponent comp){
        // set inset of constraints
        constraints.insets = defaultInset;
        
        layout.setConstraints(comp,constraints);
        add(comp);
    }

    public GridBagConstraints constraint(String ... paras){
        GridBagConstraints constraints=new GridBagConstraints();
        for(String para:paras){
            switch(para){
                case "fillY":
                constraints.fill = GridBagConstraints.VERTICAL;
                break;
                case "fillX":
                constraints.fill = GridBagConstraints.HORIZONTAL;
                break;
                case "fillBoth":
                constraints.fill = GridBagConstraints.BOTH;
                break;
                default:
            }
        }
        return constraints;
    }
}
