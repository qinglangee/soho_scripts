import java.awt.*;
import javax.swing.*;


/**
 * GridBag 适合自由组合的界面，自由度相对较高，但需要先把界面分成同等的小格子
 * 类似于一个大粒度的坐标系统。
 * 添加子组件后，格子大小会根据组件大小变化。 保证是对齐的。 
 */
public class GridBagDemo extends JPanel{

    /**
     * http://c.biancheng.net/view/1212.html
     * 
     *  4. ipadx 和 ipady
指定组件显示区域的内部填充，即在组件最小尺寸之外需要附加的像素数，默认值为 0。
5. insets
指定组件显示区域的外部填充，即组件与其显示区域边缘之间的空间，默认组件没有外部填充。
6. anchor
指定组件在显示区域中的摆放位置。可选值有 GridBagConstraints.CENTER（默认值）、GridBagConstraints.NORTH、GridBagConstraints.
NORTHEAST、GridBagConstraints.EAST、GridBagConstraints.SOUTH、GridBagConstraints.SOUTHEAST、GridBagConstraints.WEST、GridBagConstraints.SOUTHWEST 以及 GridBagConstraints.NORTHWEST。
7. weightx 和 weighty
用来指定在容器大小改变时，增加或减少的空间如何在组件间分配，默认值为 0，即所有的组件将聚拢在容器的中心，多余的空间将放在容器边缘与网格单元之间。weightx 和 weighty 的取值一般在 0.0 与 1.0 之间，数值大表明组件所在的行或者列将获得更多的空间。 
     */
    public GridBagDemo(){
        
        // 使用辅助方法添加
        addGrid();

        // 使用 relative
        addGrid2();

        // 使用fill
        addGrid3();
        // ipadx ipady
        addGrid4();
        addGrid5();
        addGrid6();
    }

    private void addGrid(){

        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(Color.yellow);
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        int x=0, y=0;

        addToGrid(panel, new JButton("b1"), 0, y, 1,1);
        addToGrid(panel, new JButton("b2"), 1, y++, 2,1);

        addToGrid(panel, new JButton("b3 ..."), 1, y, 2,1);
        addToGrid(panel, new JButton("精确指定坐标排列"), 3, y++, 2,1);
        
        addToGrid(panel, new JLabel("Label"), 0, y, 1,1);
        addToGrid(panel, new JTextField(), 1, y, 2,1);


        add(panel);
    }


    
    private void addToGrid(JPanel parent, Component comp, int x, int y, int width, int height){
        GridBagLayout gridBagLayout = (GridBagLayout)parent.getLayout();
        GridBagConstraints gridBagConstraints=new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gridBagConstraints.fill=GridBagConstraints.BOTH;//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况 
        //NONE：不调整组件大小。 
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。 
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。 
        //BOTH：使组件完全填满其显示区域。
        gridBagConstraints.gridx=x;
        gridBagConstraints.gridy=y;
        gridBagConstraints.gridwidth=width;                                             
        gridBagConstraints.gridheight=height;
        gridBagLayout.setConstraints(comp, gridBagConstraints);
        parent.add(comp);
    }

    private void addGrid2(){
        JPanel panel = Util.panel(Color.blue);
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        GridBagConstraints cons = new GridBagConstraints();//实例化这个对象用来对组件进行管理
        // gridwidth 设置每个格子在宽度上占几个格子， 类似 colspan, 默认就是1
        cons.gridwidth=1;
        cons.gridheight=1;

        cons.gridy = GridBagConstraints.RELATIVE;

        JButton b1 = new JButton("Relative可以自动排列,不会换行");
        JButton b2 = new JButton("Button2");
        JButton b3 = new JButton("Button3");
        JButton b4 = new JButton("Button4");
        
        gridBagLayout.setConstraints(b1, cons);
        gridBagLayout.setConstraints(b2, cons);
        gridBagLayout.setConstraints(b3, cons);
        gridBagLayout.setConstraints(b4, cons);
        
        cons.gridy = 2; // 开始第二行
        JButton b5 = new JButton("Button5");
        JButton b6 = new JButton("Button6");
        JButton b7 = new JButton("Button7");
        gridBagLayout.setConstraints(b5, cons);
        gridBagLayout.setConstraints(b6, cons);
        gridBagLayout.setConstraints(b7, cons);

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(b7);
        add(panel);
    }

    private void addGrid3(){
        /**  指定组件填充网格的方式，可以是如下值：
         * GridBagConstraints.NONE（默认值）、
         * GridBagConstraints.HORIZONTAL（组件横向充满显示区域，但是不改变组件高度）、
         * GridBagConstraints.VERTICAL（组件纵向充满显示区域，但是不改变组件宽度）以及 
         * GridBagConstraints.BOTH（组件横向、纵向充满其显示区域）。 */

         
        JPanel panel = Util.panel(Color.green);
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        GridBagConstraints cons = new GridBagConstraints();//实例化这个对象用来对组件进行管理
        cons.gridwidth=GridBagConstraints.RELATIVE;
        cons.gridheight=GridBagConstraints.RELATIVE;
        JButton b1 = new JButton("fill 的不同填充方式");
        b1.setPreferredSize(new Dimension(250, 250));
        gridBagLayout.setConstraints(b1, cons);

        JButton b2 = new JButton("VER");
        // fill 只是在格子大于控件时起作用，也就是别的控件把格子撑大了的时候。 没有别人撑大，格子会贴到控件大小
        cons.fill = GridBagConstraints.VERTICAL;
        gridBagLayout.setConstraints(b2, cons);
        
        cons.gridy = 1;
        JButton b3 = new JButton("HOR");
        cons.fill = GridBagConstraints.HORIZONTAL;
        gridBagLayout.setConstraints(b3, cons);


        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        add(panel);
    }

    private void addGrid4(){

        JPanel panel = Util.panel(Color.green);
        panel.setPreferredSize(new Dimension(350, 350));
        GridBagLayout gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        GridBagConstraints cons = new GridBagConstraints();//实例化这个对象用来对组件进行管理
        cons.gridx = 0;
        cons.gridy = 0;
        JButton b1 = new JButton("这里有个格子");
        b1.setPreferredSize(new Dimension(150, 50));
        gridBagLayout.setConstraints(b1, cons);

        
        JButton b3 = new JButton("这里也有个格子");
        b3.setPreferredSize(new Dimension(150, 50));
        cons.gridx = 1;
        cons.gridy = 0;
        cons.fill = GridBagConstraints.BOTH;
        gridBagLayout.setConstraints(b3, cons);

        /**
         * REMAINDER 是占据没人占的格子
         * 如果没有空格子，REMAINDER不会占据容器剩余空间
         * REMAINDER 只是占了，并没有放大。 然后设置了 fill 才会在空间大的时候放大。  
         */
        JButton b2 = new JButton("REMAINDER");
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.gridheight = GridBagConstraints.REMAINDER;
        cons.gridx = 0;
        cons.gridy = 1;
        cons.fill = GridBagConstraints.BOTH;
        gridBagLayout.setConstraints(b2, cons);



        
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        add(panel);
    }
    private void addGrid5(){
    }
    private void addGrid6(){
        
        add(new TestPane());
    }

    public class TestPane extends JPanel {

        public TestPane() {

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = gbc.REMAINDER;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.BOTH;

            JTextArea patch = new JTextArea(10, 30);

            add(new JScrollPane(patch), gbc);

            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            for (int index = 0; index < 6; index++) {
                add(new JButton("Button #" + index), gbc);
                gbc.gridy++;
            }

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.SOUTH;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(new JButton("Play >"), gbc);


        }

    }

    public static void main(String[] args) {
        new DemoTest(new GridBagDemo());
    }
}
