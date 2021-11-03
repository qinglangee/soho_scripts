package nl.rug.aoop.asteroids.network;

import java.io.IOException;
import java.net.*;

public class NetClient {
    public static void main(String[] args) throws UnknownHostException {
        String content = "";
        String str = "";
        str += "1234567890";
        for(int i=0;i<8;i++){
        }
        for(int i=0;i<10;i++){
            content += str + "\n";
        }
        DatagramSocket ds = null;
        DatagramPacket dpSend = null;
        InetAddress ia = InetAddress.getByName("127.0.0.1");
        int port = 3021;

        try {
            ds = new DatagramSocket();
            for (int i = 0; i < 5; i++) {
                byte[] data = ("I am UDP" + i + " " + content).getBytes("utf-8");
                dpSend = new DatagramPacket(data, data.length, ia, port);
                ds.send(dpSend);
                Thread.sleep(1000);
            }
            ds.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
