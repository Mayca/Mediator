package Mediator.Model;

import Mediator.messages.Message;

public abstract class MediatorListener {
    
    public abstract void sendResponse(Message message);

}
