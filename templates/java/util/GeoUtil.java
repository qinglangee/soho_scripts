package util;

import static java.lang.Math.*;

public class MathUtil {

    // earth radius  km
    private static final double EARTH_R = 6378.137; 
    // private static final double EARTH_R = 6371; 

    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    
    /**
     * 
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return distance  km
     */
    public static double getDistance(double lat1, double lng1, double lat2, double lng2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);

        double distance = 2 * asin(sqrt(pow(sin(a/2), 2) + 
            cos(radLat1) * cos(radLat2) * pow(sin(b/2), 2)));

        distance *= EARTH_R;
        distance = round(distance*100000.0) / 100000.0;
        return distance;
    }
    /**
     * 
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return distance  km
     */
    public static double getDistance2(double lat1, double lng1, double lat2, double lng2){
        lat1 = lat1  * Math.PI / 180;
        lat2 = lat2  * Math.PI / 180;
        lng1 = lng1  * Math.PI / 180;
        lng2 = lng2  * Math.PI / 180;

        double cos = Math.cos(lat2) * Math.cos(lat1) * Math.cos(lng2 -lng1) + Math.sin(lat1) * Math.sin(lat2);
        return EARTH_R * Math.acos(cos);
    }
    
    public static void main(String[] args) {
        System.out.println(getDistance(0, 113, 0, 114));
        System.out.println(getDistance2(0, 113, 0, 114));
    }
}
