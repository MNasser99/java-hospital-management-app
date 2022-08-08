package oosd.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherLoginController {

    Alert a = new Alert(Alert.AlertType.NONE);

    public TextField teacherLoginUsernameTF;
    public PasswordField teacherLoginPasswordTF;
    public Button teacherLoginBtn;
    public Button teacherLoginBackBtn;

    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == teacherLoginBackBtn){
            Parent root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
            mainPage.window.setTitle("College Gateway");
            mainPage.window.setScene(new Scene(root, 450, 450));
            mainPage.window.show();
        } else if(actionEvent.getSource() == teacherLoginBtn){
            String username = teacherLoginUsernameTF.getText();
            String password = teacherLoginPasswordTF.getText();

            if(username.equals("") || password.equals("")){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Error: All text fields must be filled to enter");
                a.show();
            } else {
                Connection conn = DataConnection.connectDb();
                String query = "SELECT * FROM faculty WHERE username=? AND password=?";
                try {
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet set = statement.executeQuery();

                    if (set.next()){
                        int id = set.getInt("faculty_id");
                        String fullname = set.getString("full_name");
                        Teacher.SetVars(id, fullname);
                        Parent root = FXMLLoader.load(getClass().getResource("teacherControlPanel.fxml"));
                        mainPage.window.setTitle("Teacher Control Panel");
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
