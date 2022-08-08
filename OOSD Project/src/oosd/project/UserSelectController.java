package oosd.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class UserSelectController{

    @FXML
    Button adminBtn;
    @FXML
    Button teacherBtn;
    @FXML
    Button studentBtn;
    @FXML
    Button mainPageExitBtn;

    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == adminBtn){
            Parent root = FXMLLoader.load(getClass().getResource("adminLogin.fxml"));
            mainPage.window.setTitle("Admin Login");
            mainPage.window.setScene(new Scene(root));
        } else if(actionEvent.getSource() == teacherBtn){
            Parent root = FXMLLoader.load(getClass().getResource("teacherLogin.fxml"));
            mainPage.window.setTitle("Teacher Login");
            mainPage.window.setScene(new Scene(root));
        } else if(actionEvent.getSource() == studentBtn){
            Parent root = FXMLLoader.load(getClass().getResource("studentLogin.fxml"));
            mainPage.window.setTitle("student Login");
            mainPage.window.setScene(new Scene(root));
        } else if(actionEvent.getSource() == mainPageExitBtn){
            mainPage.window.close();
        }

    }
}
