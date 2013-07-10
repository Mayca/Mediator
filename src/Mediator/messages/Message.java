package Mediator.messages;

public abstract class Message {
    protected String channel;

    public Message(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
    
}
