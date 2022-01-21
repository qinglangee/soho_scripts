
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import static javax.swing.SpringLayout.*;
import javax.swing.WindowConstants;
 
public class SpringLayoutDemo {
 
    public static void main(String[] args) {
        JFrame jf = new JFrame("演示弹性布局"); //创建窗口
        jf.setSize(500, 300);
        JPanel jp = new JPanel();
        jf.setContentPane(jp);
        SpringLayout springLayout = new SpringLayout();
        jp.setLayout(springLayout);//设置布局格式为弹簧式
        
        //标签组件1的位置约束
        JLabel label1 = new JLabel("项目名称： ");
        jp.add(label1);
        springLayout.putConstraint(NORTH, label1, 10, NORTH, jp);  //标签1北侧——>容器北侧
        springLayout.putConstraint(WEST, label1, 10, WEST, jp);    //标签1西侧——>容器西侧 10
        
        //文本框组件的位置约束
        JTextField txtF1 = new JTextField();
        jp.add(txtF1);
        springLayout.putConstraint(NORTH, txtF1, 10, NORTH, jp);//文本框北侧——>容器北侧
        springLayout.putConstraint(WEST, txtF1, 10, EAST, label1);//文本框西侧——>主题标签东侧
        springLayout.putConstraint(EAST, txtF1, -50, EAST, jp);// 文本框东侧——>容器东侧
        
        //标签组件2的位置约束
        JLabel label2= new JLabel("项目内容： ");
        jp.add(label2);
        springLayout.putConstraint(NORTH, label2, 20, SOUTH, label1);// 内容标签北侧(参照文本框是因为文本框高)——>主题文本框南侧
        springLayout.putConstraint(WEST, label2, 10, WEST, jp);// 内容标签西侧——>容器西侧
 
        //滚动面板,内置文本框架
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(new JTextArea());
        jp.add(scrollPane);
        springLayout.putConstraint(NORTH, scrollPane, 20, SOUTH, txtF1);// 滚动面板北侧——>文本框南侧
        springLayout.putConstraint(WEST, scrollPane, 10, EAST, label2);// 滚动面板西侧——>内容标签东侧
	springLayout.putConstraint(EAST, scrollPane, -50, EAST, jp);// 滚动面板东侧——>容器东侧
        
        JButton Btn01 = new JButton("清空");
        jp.add(Btn01);
	springLayout.putConstraint(SOUTH, Btn01, -50, SOUTH,jp);// 按钮1南侧——>容器南侧
        //springLayout.putConstraint(WEST, resetButton, 50, WEST, jp);    //按钮1西侧——>容器西侧 10
        
        JButton Btn02 = new JButton("新增");
	jp.add(Btn02);
	springLayout.putConstraint(SOUTH, Btn02, -50, SOUTH,jp);// 按钮2南侧——>容器南侧
	springLayout.putConstraint(EAST, Btn02, -50, EAST,jp);// 按钮2东侧——>容器东侧
	springLayout.putConstraint(SOUTH, scrollPane, -20, NORTH,Btn02);// 滚动面板南侧——>“确定”按钮北侧
	springLayout.putConstraint(EAST, Btn01, -50, WEST,Btn02);// “清空”按钮东侧——>“确定”按钮西侧
        
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);  // 显示窗口
        
    }    
}