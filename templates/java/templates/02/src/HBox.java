import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

import java.awt.Component;

public class HBox {
    
    protected Box box;

    protected boolean useGlue;

    protected int gap = 0;


    public HBox(){
        this(0);
    }

    public HBox(int gap){
        this.gap = gap;
        createBox();
    }
    
    protected void createBox(){
        box = Box.createHorizontalBox();
    }
    
    public void setPadding(int size){
        setPadding(size, size, size, size);
    }
    public void setPadding(int top, int left, int bottom, int right){
        box.setBorder(new EmptyBorder(top, left, bottom, right));
    }


    public void add(Component comp){
        box.add(comp);
        checkGlue();
    }

    public void checkGlue(){
        if(useGlue){
            box.add(Box.createHorizontalGlue());
        }
        if(gap > 0){
            box.add(Box.createHorizontalStrut(gap));
        }
    }

    public Box getBox(){
        return box;
    }

    public void setUseGlue(boolean useGlue){
        this.useGlue = useGlue;
        checkGlue();
    }
}
