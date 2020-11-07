import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client 启动了一个线程来接收消息。 就不用像 Server 一样在线程里启动了。 
 */
public class SocketClient {

    String host; // 服务器地址
    int port; // 服务器端口
    boolean online = true;
    ClientLogic logic = null;

    PrintWriter writer = null;

    private static SocketClient instance;

    public static SocketClient getInstance(){
        if(instance == null){
            instance = new SocketClient(Config.HOST, Config.PORT);
            new Thread(()->{
                instance.waitMsg();
            }).start();
        }
        return instance;
    }

    public SocketClient(String host, int port) {
        this.host = host;
        this.port = port;
        logic = new ClientLogic(this);

    }

    // 等待接收处理消息
    public void waitMsg(){
        
        try (Socket s = new Socket(host, port);
            
        ){
            writer = new PrintWriter(s.getOutputStream());

            try(BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));){

                while(online){
                    String line = reader.readLine();
                    logic.processMsg(line);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 发送消息到服务器
    public void sendMsg(String msg){
        writer.println(msg);
        writer.flush();
        Log.debug("client send:" + msg);
    }
    
}
