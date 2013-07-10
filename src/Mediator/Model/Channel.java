package Mediator.Model;

import Mediator.messages.MasterRequestMessage;
import Mediator.messages.MasterResponseMessage;
import Mediator.messages.Message;
import Mediator.messages.SlaveRequestMessage;
import Mediator.messages.SlaveResponseMessage;

public class Channel {

    private String name;
    private Master master;
    private Slave slave;

    public Channel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Master getMaster() {
        return master;
    }

    public Slave getSlave() {
        return slave;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public void setSlave(Slave slave) {
        this.slave = slave;
    }

    public void include(Message message) {
        if (message instanceof MasterRequestMessage) {
            include((MasterRequestMessage) message);
        }
        if (message instanceof SlaveRequestMessage) {
            include((SlaveRequestMessage) message);
        }
    }

    private void include(MasterRequestMessage message) {
        this.master = new Master(message.getIp(), message.getPort());
    }

    private void include(SlaveRequestMessage message) {
        this.slave = new Slave(message.getIp(), message.getPort());
    }

    public boolean isReady() {
        return (this.master != null && this.slave != null);
    }

    public void setPort(int port) {
        this.master.setPort(port);
        this.slave.setPort(port);
    }

    public Message getResponse(String type) {
        if (type.equals("SLAVE"))
            return getSlaveResponse();        
        if (type.equals("MASTER"))
            return getMasterResponse();
        return null;
    }

    private Message getMasterResponse() {
        return (new MasterResponseMessage(this.name, this.slave.getIp(), this.slave.getPort()));
    }

    private Message getSlaveResponse() {
        return (new SlaveResponseMessage(this.name, this.master.getIp(), this.master.getPort()));
    }
}
