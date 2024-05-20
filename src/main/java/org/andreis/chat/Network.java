package org.andreis.chat;

import javafx.scene.Scene;
import javafx.scene.control.Control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    private final String SERVER_ADDR = "localhost";
    private String nick="";
    private final int SERVER_PORT = 8189;
    private Socket socket;
    private DataInputStream in;
    public DataOutputStream out;
    private HelloController ctrl;
    public Network(HelloController controller){
        ctrl=HelloApplication.main.getController();
        try {

            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public boolean successReg(){
        try {

            
            String s=in.readUTF();

            
            if (s.startsWith("/authok")) {
                
                nick=s.split(" ")[1];
                HelloApplication.st.setScene(new Scene(HelloApplication.main.load(), 600, 400));
                HelloApplication.network.openConnection();
                return true;
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
    public void openConnection() throws IOException {

        new Thread(() -> {
            while (true) {
                ctrl = HelloApplication.main.getController();
                try {

                    String str = in.readUTF();
                    System.out.println(str);
                    if(!str.startsWith("/clients")) {
                        try {
                            System.out.println(nick);
                            if (!str.startsWith(nick)) {
                                ctrl.printMSG(str);
                                ctrl.seeend();
                            }
                        } catch (Exception e) {

                        }
                    }
                    else{
                        String[] cl = str.split(" ");
                        ctrl.getClientList().getItems().clear();
                        for(int i = 1; i<cl.length; i++){
                            ctrl.getClientList().getItems().add(cl[i]);
                        }

                    }

                } catch (IOException | IllegalStateException e) {
                    //e.printStackTrace();
                }
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
