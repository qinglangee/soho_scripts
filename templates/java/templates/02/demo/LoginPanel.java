
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginPanel extends GridPanel implements ActionListener{
    private static final long serialVersionUID = -796884517813846758L;

    JLabel nameL = new JLabel("用户名:");
    JTextField nameT = new JTextField(20);
    JLabel passL = new JLabel("密  码:");
    JPasswordField passT = new JPasswordField(20);
    JButton loginBtn = new JButton("登录");
    GridBagLayout layout;
    public LoginPanel(){
        // 设置边框
        // setBorder(BorderFactory.createLineBorder(Color.red, 2));


        addToRow(nameL);
        addToRow(nameT);
        addToNewRow(passL);
        addToRow(passT);
        addComp(loginBtn, 1, 2, constraint("fillX")); 

        loginBtn.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();
        if(src == loginBtn){
            System.out.println("click login");
        }
    }
    public static void main(String[] args) {
        new DemoTest(new LoginPanel());
    }
}
