
import java.util.*;
import java.io.*;

/**
 * 文件工具类，提供读写文件的方法
 * 
 */
public class FileUtil {
    // 把内容写入文件
    public static void writeFile(String filename, String content) {
        writeFile(filename, content, "utf-8");
    }
    // 把内容写入文件
    public static void writeFile(String filename, String content, String encode) {
        File file = new File(filename);
        try {
            // FileOutputStream out = new FileOutputStream(file);
            // BufferedOutputStream bos = new BufferedOutputStream(out);
            OutputStreamWriter isw = new OutputStreamWriter(new FileOutputStream(file), encode);
            isw.write(content);
            isw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 从文件中读取内容
    public static ArrayList<String> readLines(String filename) {
        return readLines(filename, "utf-8");
    }
    
    // 从文件中读取内容
    public static ArrayList<String> readLines(String filename, String encode) {
        File file = new File(filename);
        ArrayList<String> result = new ArrayList<String>();
        if (!file.exists()) {
            return result;
        }
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), encode);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while(line != null){
                result.add(line);
                line = br.readLine();
            }
            br.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}