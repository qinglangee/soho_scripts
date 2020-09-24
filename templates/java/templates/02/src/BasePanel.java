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
    JPanel center = new JPanel();  // 子类组件添加的位置

    private boolean useScroll = false;
    private boolean useTop = false;

    // 设置面板的属性
    public void init(String ... paras){
        for(String para : paras){
            switch(para){
                case "useScroll":
                useScroll = true;
                break;
                case "useTop":
                useTop = true;
                break;
                default:
            }
        }
    }


    public BasePanel(String ... paras){
        // 设置布局方式, JPanel 的默认布局方式是 FlowLayout
        setLayout(new BorderLayout(5,5));
        
        if(useTop){
            add(top, BorderLayout.NORTH);
        }

        init(paras);

        center.setLayout(new BorderLayout(5,5));
        if(useScroll){
            JScrollPane sp = new JScrollPane(center);
            add(sp, BorderLayout.CENTER);
        }else{
            add(center, BorderLayout.CENTER);
        }

        
    }

    // 一次性添加多个组件
    public void addComponent(JComponent ... btns){
        if(!useTop){
            System.out.println("set useTop first!!");
        }
        for(int i=0;i<btns.length;i++){
            top.add(btns[i]);
        }
    }


    
}