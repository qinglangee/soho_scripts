public class Log {
    
    public static int level = 0;

    public static void log(String content){
        System.out.println(content);
    }
    public static void debug(String content){
        if(level<=0){

            log("DEBUG- " + content);
        }
    }

    public static void info(String content){
        if(level<=1){

            log("INFO- " + content);
        }
    }
}
