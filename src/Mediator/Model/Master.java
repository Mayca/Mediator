package Mediator.Model;

public class Master {
   private String ip;
   private int port;

    public Master(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    void setPort(int port) {
        this.port = port;
    }

         
}
