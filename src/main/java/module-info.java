module org.andreis.chat {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.andreis.chat to javafx.fxml;
    exports org.andreis.chat;
}