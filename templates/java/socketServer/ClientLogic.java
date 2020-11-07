public class ClientLogic {
    SocketClient client;

    public ClientLogic(SocketClient client){
        this.client = client;
    }

    public void processMsg(String line){
        System.out.println("client receive:" + line);
    }
}
