package org.andreis.chat;

import javafx.scene.control.Control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private HelloController ctrl;
    public Network(HelloController controller){
        ctrl=controller;
    }
    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                String str = in.readUTF();
                ctrl.printMSG("Server",str);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public void sendMSG(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}