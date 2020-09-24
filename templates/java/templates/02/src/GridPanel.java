
import java.awt.*;

import javax.swing.*;
import java.util.*;

public class GridPanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    
    private int gridY = 0; // 新组件添加到的行
    
    private GridBagLayout layout; // 网格布局
    protected Insets defaultInset = new Insets(5, 5, 5, 5); // 组件默认间隔 上左下右

    public GridPanel(){
        layout = new GridBagLayout();
        setLayout(layout);
    }

    // 添加组件到同一行
    public void addToRow(JComponent ... comps){
        for(JComponent comp : comps){
            addToRow(comp, gridY);
        }
    }
    // 添加组件到新的行
    public void addToNewRow(JComponent comp){
        gridY++;
        addToRow(comp, gridY);
    }

    // 添加组件，指定行数
    public void addToRow(JComponent comp, int y){
        addToRow(comp, y, null);
    }
    // 添加组件，指定行数
    public void addToRow(JComponent comp, int gridY, GridBagConstraints c){
        GridBagConstraints constraints= c!=null?c:new GridBagConstraints();
        constraints.gridy=gridY;
        addComp(constraints, comp);
    }

    // 添加组件，指定行列数
    public void addComp(JComponent comp, int x, int y){
        addComp(comp, x, y, null);
    }
    
    // 添加组件，指定行列数
    public void addComp(JComponent comp, int x, int y, GridBagConstraints c){
        GridBagConstraints constraints= c!=null ? c : new GridBagConstraints();
        constraints.gridx=x;
        constraints.gridy=y;
        addComp(constraints, comp);
    }

    // 设置添加组件的一些统一操作
    public void addComp(GridBagConstraints constraints, JComponent comp){
        constraints.insets = defaultInset;// 设置控件的空白
        
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
