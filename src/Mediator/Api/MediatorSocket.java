package Mediator.Api;

import Mediator.Model.Mediator;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class MediatorSocket{

    private ServerSocket socket;
    private List<MediatorThread> threadList;
    private Mediator mediator;
   
    public MediatorSocket(int port) throws IOException {
        this.socket = new ServerSocket(port);
        this.threadList = new ArrayList<MediatorThread>();
        this.mediator = new Mediator();
        waitConnections();
    }

    private void waitConnections() throws IOException {
        while (true)
            startThread();            
    }
    
    private void addToSocketList(MediatorThread socketThread) {
        this.threadList.add(socketThread);
      //  this.threadList.get(this.threadList.size()-1).addListener(this);
    }

    
    private void startThread() throws IOException {
        MediatorThread mediatorThread = new MediatorThread(socket.accept(), this.mediator);
        mediatorThread.start();
        addToSocketList(mediatorThread);
    }

    
}
