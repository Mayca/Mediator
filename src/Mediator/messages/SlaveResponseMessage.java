package Mediator.messages;

public class SlaveResponseMessage extends ResponseMessage{
    private String ip;
    private int port;

    public SlaveResponseMessage(String channel, String ip, int port) {
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
