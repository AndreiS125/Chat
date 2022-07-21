package org.andreis.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class HelloController {
    private ArrayList<String> messagesToSend=new ArrayList<String>();
    @FXML
    private ListView<String> allMessages;

    @FXML
    private ListView<String> allUsers;

    @FXML
    private TextField toSend;

    @FXML
    void keysnd(KeyEvent event) {
        //entersend event
    }
    @FXML
    void msgsend(MouseEvent event) {
        for(String s:messagesToSend){
            allMessages.getItems().add(s);
        }

        messagesToSend.clear();
    }
    @FXML
    void initialize() {

        for(String s:messagesToSend){
            allMessages.getItems().add(s);
        }

    }

    @FXML
    void send(ActionEvent event) {
        if(!toSend.getText().equals("")){
            allMessages.getItems().add(toSend.getText());
            HelloApplication.network.sendMSG(toSend.getText());
            toSend.setText("");
        }

    }
    void printMSG(String msg) {
        if(!msg.equals("")){
            messagesToSend.add(msg);


        }

    }

    void seeend() {

            allMessages.scrollTo(allMessages.getItems().size());




    }

    ListView getClientList() {
        return allUsers;

    }

}
