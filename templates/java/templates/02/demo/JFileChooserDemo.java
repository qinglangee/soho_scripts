import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JFileChooserDemo extends BaseDemoJPanel{
    public JFileChooserDemo() {
        JButton open = createButton(this, "open file", 0, 0);

        open.addActionListener(e->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setDialogTitle("open file");
            // set file filter
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "jpeg", "bmp", "gif");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                BaseDialog.showInfo("open file: " + file.getAbsolutePath());
            }
        });
    }

    public static void main(String[] args) {
        new DemoTest(new JFileChooserDemo());
    }
}
