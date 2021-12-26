import javax.swing.JOptionPane;
import java.awt.Component;


public class BaseDialog {
    
    public static void showErr(String msg){
        JOptionPane.showMessageDialog(null, msg, "错误",
                            JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfo(String msg){
        JOptionPane.showMessageDialog(null, msg, "信息",
                            JOptionPane.INFORMATION_MESSAGE);
    }

    public static String showInput(String msg){
        String content = JOptionPane.showInputDialog(null, msg);
        return content;
    }

    // 两个选项
    public static boolean confirm(String msg, String title){
        Component parent = null;
        int ret = JOptionPane.showConfirmDialog(parent, msg, title, JOptionPane.YES_NO_OPTION);
        return ret == JOptionPane.OK_OPTION;
    }
    // 三个选项， 有取消
    public static boolean confirm(String msg){
        Component parent = null;
        int ret = JOptionPane.showConfirmDialog(parent, msg);
        return ret == JOptionPane.OK_OPTION;
    }

}