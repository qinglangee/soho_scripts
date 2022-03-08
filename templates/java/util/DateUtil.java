package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static String now(){
        Date date = Calendar.getInstance().getTime();
        return format.format(date);
    }

    public static String now(int delta){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, delta);
        Date date = c.getTime();
        return format.format(date);
    }
    public static String day(int delta){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.add(Calendar.SECOND, delta);
        Date date = c.getTime();
        return format.format(date);
    }

    public static void main(String[] args) {
        System.out.println(day(0));
        System.out.println(day(30000));
    }
}
