import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JMenuDemo extends BaseFrame{

    public JMenuDemo() {
        super("JMenuDemo"); // 设置窗口标题
        setSize(300, 200); // 设置窗口大小
        setLocation(200, 200); // 设置窗口位置
        
        JMenuBar menuBar = new JMenuBar(); // 创建菜单栏
        setJMenuBar(menuBar); // 将菜单栏添加到窗口
        // add two menu items to menu bar
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem open = new JMenuItem("Open");
        file.add(open);
        open.addActionListener(e->{
            JOptionPane.showMessageDialog(null, "Click open", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        menuBar.add(new JMenu("Edit"));
    }


    public static void main(String[] args) {
        JMenuDemo jMenuDemo = new JMenuDemo();
        jMenuDemo.setVisible(true);
    }
    
}
