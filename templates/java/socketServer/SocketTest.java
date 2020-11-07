public class SocketTest {
    public static void main(String[] args) {
        SocketServer server = new SocketServer(Config.PORT);
        Thread thread = new Thread(new Runnable(){
            public void run(){
                server.start();
            }
        });
        thread.start();

        SocketClient client = SocketClient.getInstance();

        try{

            Thread.sleep(1000);
        }catch(Exception e){}
        client.sendMsg("echo");
    }
    
}
