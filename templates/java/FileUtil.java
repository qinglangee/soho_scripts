
import java.util.*;
import java.io.*;

public class FileUtil {
    
    private static final String DEFAULT_CHARSET = "utf-8";
    /**
     * 文本内容写入文件，默认使用 utf-8 编码
     * @param filename
     * @param content
     */
    public static void writeFile(String filename, String content) {
        writeFile(filename, content, DEFAULT_CHARSET);
    }
    /**
     * 文本内容写入文件
     * @param filename
     * @param content
     * @param charset
     */
    public static void writeFile(String filename, String content, String charset) {
        File file = new File(filename);
        try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), charset);
        BufferedWriter bw = new BufferedWriter(osw);

        ) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 文本内容追加到文件后面，默认使用 utf-8 编码
     * @param filename
     * @param content
     */
    public static void appendFile(String filename, String content) {
        appendFile(filename, content, DEFAULT_CHARSET);
    }
    /**
     * 文本内容追加到文件后面
     * @param filename
     * @param content
     * @param charset
     */
    public static void appendFile(String filename, String content, String charset) {
        File file = new File(filename);
        try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), charset);
        BufferedWriter bw = new BufferedWriter(osw);) {
            bw.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件行的内容到一个列表中
     * @param filename
     * @return
     */
    public static List<String> readLines(String filename) {
        return readLines(filename, DEFAULT_CHARSET);
    }
    public static List<String> readLines(String filename, String charset) {
        File file = new File(filename);
        ArrayList<String> result = new ArrayList<>();
        if (!file.exists()) {
            return result;
        }
        try(InputStreamReader fr = new InputStreamReader(new FileInputStream(file), charset);) {
            
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

    /**
     * 读取文件内容到一个字符串
     * @param filename
     * @return
     */
    public static String readFile(String filename) {
        return readFile(filename, DEFAULT_CHARSET);
    }
    public static String readFile(String filename, String charset) {
        File file = new File(filename);
        String result = "";
        if (!file.exists()) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        try(InputStreamReader fr = new InputStreamReader(new FileInputStream(file), charset)) {
            int readed = fr.read();
            while(readed != -1){

                sb.append((char)readed);
                readed = fr.read();
            }
            result = sb.toString();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}