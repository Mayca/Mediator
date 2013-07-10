package Mediator.messages;

public class SlaveRequestMessage extends RequestMessage {
    private String ip;
    private int port;

    public SlaveRequestMessage(String channel, String ip, int port) {
        super(channel);
        this.ip = ip;
        this.port = port;
    }

    public String getFmuName() {
        return channel;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
    
}
