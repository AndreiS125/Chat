package org.andreis.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistryController {

    @FXML
    private Label err;

    @FXML
    private TextField loginField;

    @FXML
    private TextField regLoginField;

    @FXML
    private TextField regPasswordField;

    @FXML
    private TextField regNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitReg;
    @FXML
    private Button submitReg1;

    @FXML
    void regPerson(ActionEvent event) {
        HelloApplication.network.sendMSG("/auth "+loginField.getText()+" "+passwordField.getText());

            if(HelloApplication.network.successReg()){

            }
            else{
                err.setVisible(false);
                err.setVisible(true);
            }


    }
    @FXML
    void regPerson1(ActionEvent event) {
        HelloApplication.network.sendMSG("/reg "+regLoginField.getText()+" "+regNameField.getText()+" "+regPasswordField.getText());

        if(HelloApplication.network.successReg()){

        }
        else{
            err.setVisible(false);
            err.setVisible(true);
        }


    }

}
