import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.stream.Collectors;
/**
 * 面板公共类
 */
public class BasePanel extends JPanel {

    private static final long serialVersionUID = -2472560774251553372L;

    

    JPanel top = new JPanel();
    JPanel center = new JPanel();


    public BasePanel(){
        // 设置布局方式
        setLayout(new BorderLayout(5,5));
        add(top, BorderLayout.NORTH);

        JScrollPane sp = new JScrollPane(center);
        center.setLayout(new BorderLayout(5,5));

        
        add(sp, BorderLayout.CENTER);
        
    }

    // 一次性添加多个组件
    public void addComponent(JComponent ... btns){
        for(int i=0;i<btns.length;i++){
            top.add(btns[i]);
        }
    }


    
}