import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket 服务器框架代码，启动后无限循环。接到客户端连接后新开线程处理。
 * 一般可以新开一个线程启动服务。
 */
public class SocketServer {
    int port;
    boolean running = true;


    public SocketServer(int port){
        this.port = port;
    }

    public void start() {
        try(ServerSocket ssocket = new ServerSocket(port)){
            while(running){
                Socket socket = ssocket.accept();
                // 服务端可以记住每个socketProcesser, 对客户端进行多端操作
                SocketProcesser socketProcesser = new SocketProcesser(socket);
                Thread socketThread = new Thread(socketProcesser);
                socketThread.start();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}

/**
 * 对每个接到的客户端连接，新建线程处理。 
 */
class SocketProcesser implements Runnable{
    Socket socket = null;
    PrintWriter writer = null;
    boolean online = true;
    ServerLogic logic = null;
    public SocketProcesser(Socket socket){
        try{

            this.socket = socket;
            logic = new ServerLogic(this);
            writer = new PrintWriter(socket.getOutputStream());
            

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 消息读取处理
    public void run(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));){
            while(online){
                String line = reader.readLine();
                if(line == null){
                    Log.debug("read null from client, close.");
                    online = false;
                    socket.close();
                    break;
                }
                logic.processMsg(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 发送消息
    public synchronized void sendMessage(String msg){
        writer.println(msg);
        writer.flush();
        Log.debug("server send:[] " + msg);
    }
}
