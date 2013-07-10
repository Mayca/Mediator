package Mediator;

import Mediator.Api.MediatorSocket;
import java.io.IOException;

public class Main {
    String[] args;
    public static void main (String[] args) throws IOException{
        MediatorSocket mediatorSocket = new MediatorSocket(4444);//Integer.valueOf(args[0]));
    }

    

}
