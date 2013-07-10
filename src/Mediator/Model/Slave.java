package Mediator.Model;

public class Slave {
      private String ip;
      private int port;

    public Slave(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

   public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

   
}
