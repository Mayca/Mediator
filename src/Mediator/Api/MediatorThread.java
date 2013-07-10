package Mediator.Api;

import Mediator.Model.Mediator;
import Mediator.Model.MediatorListener;
import Mediator.messages.MasterRequestMessage;
import Mediator.messages.Message;
import Mediator.messages.SlaveRequestMessage;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MediatorThread extends NotifyingThreads {

    private Socket socket;
    private Mediator mediator;
    private BufferedReader reader;
    private PrintWriter writer;

    public MediatorThread(Socket socket, Mediator mediator) throws IOException {
        this.socket = socket;
        this.mediator = mediator;
        this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.writer = new PrintWriter(this.socket.getOutputStream(), true);
    }

    @Override
    public int[] doRun() {
        try {
            String header = readHeader();
            mediator.send(header,readRequestMessage(header), createListener());
        }
        catch (Exception e) {
        }
        return null;
    }

    private String readHeader() throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(this.reader.readLine(), String.class);
    }

    private Message readRequestMessage(String header) throws IOException {
        Gson gson = new Gson();
        return (Message) gson.fromJson(this.reader.readLine(), getRequestClass(header));
    }

    private void writeResponse(Message message) {
        Gson gson = new Gson();
        this.writer.println(gson.toJson(message));
    }

    private Class<?> getRequestClass(String header) {
        if (header.equals("SLAVE"))
            return SlaveRequestMessage.class;
        if (header.equals("MASTER"))
            return MasterRequestMessage.class;
        return null;
    }

    private MediatorListener createListener() {
        return new MediatorListener() {
            @Override
            public void sendResponse(Message message) {
                writeResponse(message);
            }
        };
    }
}
