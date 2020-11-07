import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class MultiThreadSocketServer {
    int port;
    boolean running = true;


    public MultiThreadSocketServer(int port){
        this.port = port;
    }

    public void start() {
        try(ServerSocket ssocket = new ServerSocket(port)){
            while(running){
                Socket socket = ssocket.accept();
                ServerProcesser userThread = new ServerProcesser(socket);
                Thread socketThread = new Thread(userThread);
                socketThread.start();
                Log.debug("New client connected...");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Server server = new Server(Config.PORT);
        server.start();
    }
}

class ServerProcesser implements Runnable{
    Socket socket = null;
    PrintWriter writer = null;
    boolean online = true;
    ServerLogic logic = null;
    public ServerProcesser(Socket socket){
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
                    if(socket != null){
                        socket.close();
                    }
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
        Log.debug("server send:[" + logic.username+"] " + msg);
    }
}
