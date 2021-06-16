import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.*;
// import java.awt.*;


public class GlueBox extends Box{
    private int axis;

    private GlueBox(int axis){
        super(axis);
        this.axis = axis;
        addGlue(null);
    }

    public static GlueBox hBox(){
        return new GlueBox(BoxLayout.X_AXIS);
    }
    public static GlueBox vBox(){
        return new GlueBox(BoxLayout.Y_AXIS);
    }
    
    public Component addGlue(Component comp){
        if(comp != null)
            add(comp);

        if(axis == BoxLayout.X_AXIS){
            add(Box.createHorizontalGlue());
        }else{
            add(Box.createVerticalGlue());
        }
        return comp;
    }
}
