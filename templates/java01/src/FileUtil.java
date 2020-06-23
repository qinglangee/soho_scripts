
import java.util.*;
import java.io.*;

public class FileUtil {
    public static void writeFile(String filename, String content) {
        File file = new File(filename);
        try {
            // FileOutputStream out = new FileOutputStream(file);
            // BufferedOutputStream bos = new BufferedOutputStream(out);
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readLines(String filename) {
        File file = new File(filename);
        ArrayList<String> result = new ArrayList<String>();
        if (!file.exists()) {
            return result;
        }
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                result.add(line);
                line = br.readLine();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}