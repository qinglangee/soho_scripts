package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyUtil {

    static String defaultFile = "stocker.properties";
    private static Properties prop;
    private static Properties defaultProp;

    /**
     * get properties value from file
     * @param key
     * @return
     */
    public static String get(String key){
        if(prop == null){
            init();
        }

        String value = prop.getProperty(key);
        // System.out.println("key " + key + " " + value);
        if(value == null){
            value = defaultProp.getProperty(key);
        }
        return value;
    }

    /**
     * save properties
     * @param key
     * @param value
     */
    public static void set(String key, String value){
        if(prop == null){
            init();
        }

        prop.setProperty(key, value);
    }

    public static void init(){
        
        defaultProp = new Properties();
        defaultProp.setProperty("provider", "0");
        defaultProp.setProperty("api_key0", "c351b6qad3idcjopd5gg");
        defaultProp.setProperty("pull_url0", "https://finnhub.io/api/v1/");
        defaultProp.setProperty("push_url0", "wss://ws.finnhub.io");

        defaultProp.setProperty("api_key1", "stu_number");
        defaultProp.setProperty("pull_url1", "http://localhost:8080/");
        defaultProp.setProperty("push_url1", "ws://localhost:8090");


        defaultProp.setProperty("min_width", "500");
        defaultProp.setProperty("min_height", "400");

        defaultProp.setProperty("chart_type", "candle");
        defaultProp.setProperty("time_interval", "D");

        defaultProp.setProperty("alarm_line_color", "red");
        defaultProp.setProperty("alarm_indicator_color", "red");

        
        loadDefault();
    }

    /**
     * Load properties from default file
     */
    public static void loadDefault(){
        prop = readPropertiesFile(defaultFile);
    }

    /**
     * Save properties to file
     */
    public static void save(){
        OutputStream out;
        try {
            out = new FileOutputStream(defaultFile);
            prop.store(out, "stock settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read properties file
     * @param fileName
     * @return
     */
    public static Properties readPropertiesFile(String fileName) {
        File file = new File(fileName);
        System.out.println("Property file exist:" + file.exists());
        if(!file.exists()){
            OutputStream out;
            try {
                out = new FileOutputStream(fileName);
                defaultProp.store(out, "stock settings");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
    
}
