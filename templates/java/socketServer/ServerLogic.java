public class ServerLogic {

    SocketProcesser processer;
    public ServerLogic(SocketProcesser processer){
        this.processer = processer;
    }

    public void processMsg(String line){
        System.out.println("server receive:" + line);
        if("echo".equals(line)){
            processer.sendMessage("receive:" + line);
        }
    }
    
}
