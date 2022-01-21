import javax.swing.JPanel;

public class SpringBox extends JPanel{
    
    JPanel[] panels;

    /**
     * Init spring box
     * @param type 0 horizontal  1 vertical
     * @param size sub panel size
     */
    public SpringBox(int type, int size){
        panels = new JPanel[size];
    }
}
