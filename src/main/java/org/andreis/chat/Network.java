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

            System.out.println("Запрос данных..");
            String s=in.readUTF();

            System.out.println("Ответ получен..");
            if (s.startsWith("/authok")) {
                System.out.println("Регистрация успешно..");
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
                    try {
                        System.out.println(nick);
                        if(!str.startsWith(nick)) {
                            ctrl.printMSG(str);
                        }
                    } catch (Exception e) {

                    }

                } catch (IOException e) {
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
