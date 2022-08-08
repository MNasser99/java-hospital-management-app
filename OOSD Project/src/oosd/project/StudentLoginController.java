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

public class StudentLoginController {
    Alert a = new Alert(Alert.AlertType.NONE);

    public TextField studentLoginUsernameTF;
    public PasswordField studentLoginPasswordTF;
    public Button studentLoginBtn;
    public Button studentLoginBackBtn;
    public Button studentLoginSignupBtn;

    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == studentLoginBackBtn){
            Parent root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
            mainPage.window.setTitle("College Gateway");
            mainPage.window.setScene(new Scene(root));
            mainPage.window.show();
        } else if(actionEvent.getSource() == studentLoginSignupBtn){
            Parent root = FXMLLoader.load(getClass().getResource("studentSignup.fxml"));
            mainPage.window.setTitle("Student Registration");
            mainPage.window.setScene(new Scene(root));
            mainPage.window.show();
        } else if(actionEvent.getSource() == studentLoginBtn){
            String username = studentLoginUsernameTF.getText();
            String password = studentLoginPasswordTF.getText();

            if(username.equals("") || password.equals("")){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Error: All text fields must be filled to enter");
                a.show();
            } else {
                Connection conn = DataConnection.connectDb();
                String query = "SELECT * FROM student WHERE username=? AND password=?";
                try {
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet set = statement.executeQuery();

                    if (set.next()){
                        int id = set.getInt("id");
                        String fullname = set.getString("full_name");
                        String civilID = set.getString("civil_id");
                        String dOB = set.getString("dob");
                        String phone = set.getString("phone");
                        String email = set.getString("email");
                        String uname = set.getString("username");
                        String qualifications = set.getString("qualifications");
                        int is_valid = set.getInt("is_valid");
                        if(is_valid == 1){
                            Student.SetVars(id, fullname, civilID, dOB, phone, email, uname, qualifications);
                            Student.UpdateCourses();
                            Parent root = FXMLLoader.load(getClass().getResource("studentControlPanel.fxml"));
                            mainPage.window.setTitle("Student Control Panel");
                            mainPage.window.setScene(new Scene(root));
                            mainPage.window.show();
                        } else {
                            a.setAlertType(Alert.AlertType.ERROR);
                            a.setContentText("Error: your account hasn't been verified by an admin yet");
                            a.show();
                        }

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
