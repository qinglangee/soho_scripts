
import java.util.*;
import java.io.*;

public class FileUtil {
    
    private static final String DEFAULT_CHARSET = "utf-8";
    /**
     * Write content into file, default use utf-8 encode
     * @param filename
     * @param content
     */
    public static void writeFile(String filename, String content) {
        writeFile(filename, content, DEFAULT_CHARSET);
    }
    /**
     * Write content into file
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
     * Append content into file, default use utf-8 encode
     * @param filename
     * @param content
     */
    public static void appendFile(String filename, String content) {
        appendFile(filename, content, DEFAULT_CHARSET);
    }
    /**
     * Append content into file
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
     * Read file content into a string list.
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
     * Read file content as string
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