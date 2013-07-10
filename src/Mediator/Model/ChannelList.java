package Mediator.Model;

import java.util.ArrayList;

public class ChannelList extends ArrayList<Channel> {
    
    public Channel search(String name) {
        for (Channel channel : this)
            if (name.equals(channel.getName()))
                    return channel;
        return null;
    }
    

}
