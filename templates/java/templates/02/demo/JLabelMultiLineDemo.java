import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class JLabelMultiLineDemo extends JPanel{



    public JLabelMultiLineDemo() {
        String text = "This space contains technical articles,"
                + " blogs and discussion forums with questions and answers."
                + "作为一个Java程序员，从论坛上感受到使用Java开发程序的人越来多，"
                + "心中不免欣慰。hello";
        JLabel label = new JLabel();
        label.setSize(200, 0);//注意JLabel一定要设置宽度
//      System.out.println(label.getWidth());
        JlabelSetText(label, text);
        setLayout(new FlowLayout());
        add(label);
        setVisible(true);
    }

    void JlabelSetText(JLabel jLabel, String longString) 
             {
        StringBuilder builder = new StringBuilder("<html>");
        char[] chars = longString.toCharArray();
        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
        int start = 0;
        int len = 0;
        while (start + len < longString.length()) {
            while (true) {
                len++;
                if (start + len > longString.length())break;
                if (fontMetrics.charsWidth(chars, start, len) 
                        > jLabel.getWidth()) {
                    break;
                }
            }
            builder.append(chars, start, len-1).append("<br/>");
            start = start + len - 1;
            len = 0;
        }
        builder.append(chars, start, longString.length()-start);
        builder.append("</html>");
        jLabel.setText(builder.toString());
    }

    public static void main(String[] args) {
        new DemoTest(new JLabelMultiLineDemo());
    }
    
}
