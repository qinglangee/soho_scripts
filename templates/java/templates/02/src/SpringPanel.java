import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class SpringPanel extends JPanel{

    private int type;
    private String[] edges = new String[]{NORTH, EAST, SOUTH, WEST};
    private String[] otherEdge = new String[]{SOUTH, WEST, NORTH, EAST};
    private int[] flags = new int[]{1,-1,-1,1};

    // The normal padding of the four type
    private String[] pad1s = new String[]{WEST, NORTH, WEST, NORTH};
    // The normal padding's opposite of the four type
    private String[] pad2s = new String[]{EAST, SOUTH, EAST, SOUTH};
    
    SpringLayout layout;

    private int gap; // Gap space between comps

    /** paddings of top right bottom left */
    private Integer[] paddings = new Integer[4];
    private Map<String, Integer> paddingMap = new HashMap<>();

    /** if the single component fill container */
    private boolean fill = false;
    /** if all the component fill container */
    private boolean average = false;

    public SpringPanel(){
        this(0, 0);
    }

    public SpringPanel(int type){
        this(type, 0);
    }
    public SpringPanel(int type, int gap){
        this(type, gap, 0);
    }
    public SpringPanel(int type, int gap, int padding){
        this.type = type%4;
        this.gap = gap;
        setPadding(padding);
        layout = new SpringLayout();
        setLayout(layout);
    }

    public void setGap(int gap){
        this.gap = gap;
    }

    public void setPadding(int p){
        setPadding(p, p, p, p);
    }

    public void setFill(boolean fill){
        this.fill = fill;
    }

    public void setPadding(int top, int right, int bottom, int left){
        paddings[0] = top;
        paddings[1] = right;
        paddings[2] = bottom;
        paddings[3] = left;
        paddingMap.put(NORTH, top);
        paddingMap.put(EAST, -right);
        paddingMap.put(SOUTH, -bottom);
        paddingMap.put(WEST, left);
    }



    public void add(JComponent comp){
        Component[] components = getComponents();
        int len = components.length;
        if(len == 0){
            addFirstConstraint(comp);
        }else{
            addOtherConstraint(comp);
        }
        // set normal first padding
        layout.putConstraint(pad1s[type], comp,  paddingMap.get(pad1s[type]), pad1s[type], this);
        if(fill){
            layout.putConstraint(pad2s[type], comp,  paddingMap.get(pad2s[type]), pad2s[type], this);
        }
        super.add(comp);
    }

    public void addFirstConstraint(JComponent comp){
        layout.putConstraint(edges[type], comp, gap * flags[type], edges[type], this);
    }

    public void addOtherConstraint(JComponent comp){
        Component[] components = getComponents();
        int len = components.length;
        // layout.putConstraint(NORTH, comp, gap, SOUTH, components[len - 1]);
        layout.putConstraint(edges[type], comp, gap * flags[type], otherEdge[type], components[len - 1]);
    }

    public void finishLast(){
        int end = (type + 2)%4;
        Component[] components = getComponents();
        int len = components.length;
        layout.putConstraint(edges[end], components[len - 1], gap * flags[end], edges[end], this);
    }

    public void calcSize(){
        Component[] components = getComponents();
        int len = components.length;
        int gaps = (len - 1) * gap;
        int width = 0;
        int height = 0;
        if(type == 0 || type == 2){
            height = paddings[0] + paddings[2] + gaps;
            for(Component c : components){
                height += c.getPreferredSize().getHeight();
            }
        }else{
            width = paddings[1] + paddings[3] + gaps;
            for(Component c : components){
                width += c.getPreferredSize().getWidth();
            }
        }
        width = 100;
        setPreferredSize(new Dimension(width, height));
        System.out.println("width " + width + " " + height);
    }
}
