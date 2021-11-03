package nl.rug.aoop.asteroids.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class NetServer {

    public static void main(String[] args) {
        DatagramSocket ds = null;
        DatagramPacket dpReceive = null;
        int port = 3021;

        try {
            ds = new DatagramSocket(port);
            System.out.println("UDP服务器已启动。。。");
            byte[] b = new byte[30];
            while (ds.isClosed() == false) {
                dpReceive = new DatagramPacket(b, b.length);
                List<Byte> list = new ArrayList<>();
                try {
                    ds.receive(dpReceive);

                    byte[] Data = dpReceive.getData();
                    int len = Data.length;
                    System.out.println("UDP客户端发送的内容是：" + new String(Data, 0, len, "utf-8").trim());
                    System.out.println("UDP客户端IP：" + dpReceive.getAddress());
                    System.out.println("UDP客户端端口：" + dpReceive.getPort());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
    }
}
