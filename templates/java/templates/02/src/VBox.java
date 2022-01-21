import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JComponent;
import java.awt.Component;

public class VBox extends HBox{
    
    public VBox(){}
    public VBox(int gap){
        super(gap);
    }

    @Override
    protected void createBox(){
        box = Box.createVerticalBox();
    }

    
    public void checkGlue(){
        if(useGlue){
            box.add(Box.createVerticalGlue());
        }
        if(gap > 0){
            box.add(Box.createVerticalStrut(gap));
        }
    }
}