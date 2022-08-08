package oosd.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginController {


    Alert a = new Alert(Alert.AlertType.NONE);

    Connection conn = null;

    public TextField adminLoginUsernameTF;
    public PasswordField adminLoginPasswordTF;
    public Button adminLoginBtn;
    public Button adminLoginBackBtn;

    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == adminLoginBackBtn){
            Parent root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
            mainPage.window.setTitle("College Gateway");
            mainPage.window.setScene(new Scene(root));
            mainPage.window.show();
        } else if(actionEvent.getSource() == adminLoginBtn){
            String username = adminLoginUsernameTF.getText();
            String password = adminLoginPasswordTF.getText();

            if(username.equals("") || password.equals("")){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Error: All text fields must be filled to enter");
                a.show();
            } else {
                conn = DataConnection.connectDb();
                String query = "SELECT * FROM admins WHERE username=? AND password=?";
                try {
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet set = statement.executeQuery();

                    if (set.next()){
                        int id = set.getInt("admin_id");
                        Admin.SetVars(id, username);
                        Parent root = FXMLLoader.load(getClass().getResource("adminControlPannel.fxml"));
                        mainPage.window.setTitle("Admin Control Panel");
                        mainPage.window.setScene(new Scene(root));
                        mainPage.window.show();
                    }

                    else{
                        a.setAlertType(Alert.AlertType.ERROR);
                        a.setContentText("Error: wrong username or password");
                        a.show();
                    }


                } catch (SQLException ex) {
                    System.out.println("Error");
                }
            }
        }
    }
}
