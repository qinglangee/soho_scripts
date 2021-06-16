import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    
    public static final int DEBUG = 1;
    public static final int INFOMATION = 2;
    public static final int WARNING = 3;
    public static final int ERR = 4;
    public static final int NONE = 9;
    int level = 0;
    private String source;

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public Logger(){
        level = 1;
    }
    public Logger(int level){
        this.level = level;
    }
    public Logger(int level, String source){
        this.level = level;
        this.source = source;
    }

    public void log(String pre, Object ... args){
        Date date = new Date();
        System.out.print(formater.format(date) + " ");
        System.out.print(pre);
        if(source != null){
            System.out.print(source + " ");
        }
        for(int i=0;i<args.length;i++){
            Object obj = args[i];
            if(i > 0)
                System.out.print(" ");
            System.out.print(String.valueOf(obj));
        }
        System.out.println();
    }
    public void print(Object ... args){
        log("", args);
    }
    public void debug(Object ... args){
        if(level <= DEBUG)
            log("DEBUG: ", args);
    }
    public void info(Object ... args){
        if(level <= INFOMATION)
            log("INFO: ", args);
    }
    public void warn(Object ... args){
        if(level <= WARNING)
            log("WARN: ", args);
    }
    public void error(Object ... args){
        if(level <= ERR)
            log("ERR: ", args);
    }


}
