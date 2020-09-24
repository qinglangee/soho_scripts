import javax.swing.*;

public class BaseDialog {
    
    public static void showErr(String msg){
        JOptionPane.showMessageDialog(null, msg, "错误",
                            JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfo(String msg){
        JOptionPane.showMessageDialog(null, msg, "信息",
                            JOptionPane.INFORMATION_MESSAGE);
    }

}