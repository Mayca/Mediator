package Mediator.messages;

public class MasterResponseMessage extends ResponseMessage {
    private String ip;
    private int port;

    public MasterResponseMessage(String channel,String ip, int port) {
        super(channel);
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
    
}
