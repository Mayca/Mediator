package Mediator.Model;

import Mediator.messages.Message;
import java.util.HashMap;
import java.util.Map;

public class Mediator {

    private ChannelList channelList;
    private Map<Channel, MediatorListener> listenerMap;
    private Map<String, FreePortList> freePortMap;

    public Mediator() {
        this.channelList = new ChannelList();
        this.listenerMap = new HashMap<Channel, MediatorListener>();
        this.freePortMap = new HashMap<String, FreePortList>();
    }

    public void send(String header, Message message, MediatorListener listener) {
        Channel channel = channelList.search(message.getChannel());
        if (channel == null) {
            channel = createChannel(message, listener);
        }
        channel.include(message);
        if (channel.isReady()) {
            setPort(channel);
            sendResponses(channel, header, listener);
        }
    }

    private Channel createChannel(Message message, MediatorListener listener) {
        Channel channel = new Channel(message.getChannel());
        channelList.add(channel);
        listenerMap.put(channel, listener);
        return channel;
    }

    private void setPort(Channel channel) {
        String slaveIp = channel.getSlave().getIp();
        FreePortList freePortList = getFreePortList(slaveIp);
        setFreePort(freePortList, channel);
        this.freePortMap.put(slaveIp, freePortList);
    }

    private FreePortList getFreePortList(String slaveIp) {
        FreePortList freePortList;
        if ((freePortList = this.freePortMap.get(slaveIp)) == null) {
            freePortList = new FreePortList();
        }
        return freePortList;
    }

    private void setFreePort(FreePortList freePortList, Channel channel) {
        int port = freePortList.get(0);
        freePortList.remove(0);
        channel.setPort(port);
    }

    private void sendResponses(Channel channel, String header, MediatorListener listener) {
        Message response = channel.getResponse(header);
        listener.sendResponse(response);
        sendResponseToPair(response, header, channel);
    }

    private Message getListenerMapResponse(String header, Message response, Channel channel) {
        if (header.equals("SLAVE"))
            response = channel.getResponse("MASTER");
        if (header.equals("MASTER"))
            response = channel.getResponse("SLAVE");
        return response;
    }

    private void sendResponseToPair(Message response, String header, Channel channel) {
        response = getListenerMapResponse(header, response, channel);
        MediatorListener mediatorListener = this.listenerMap.get(channel);
        mediatorListener.sendResponse(response);
    }
}
