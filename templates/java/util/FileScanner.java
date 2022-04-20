import java.util.Scanner;
import java.util.List;
import java.io.FileInputStream;
import java.util.ArrayList;

public class FileScanner {

    /**
     * Read a file into list of lines
     * @param filename
     * @return
     */
    public static List<String> readLines(String filename){
        List<String> list = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new FileInputStream(filename));
            while(sc.hasNextLine()){
                list.add(sc.nextLine());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Read file's content as a string
     * @param filename
     * @return
     */
    public static String readFile(String filename){
        String content = null;
        return content;
    }
}
