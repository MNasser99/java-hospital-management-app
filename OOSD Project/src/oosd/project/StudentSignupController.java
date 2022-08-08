package oosd.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;

public class StudentSignupController {

    Connection conn = null;

    Alert a = new Alert(Alert.AlertType.NONE);

    public TextField studentSignupUsernameTD;
    public PasswordField studentSignupPasswordTD;
    public TextField studentSignupFullNameTD;
    public TextField studentSignupEmailTF;
    public TextField studentSignupCiviIDTD;
    public TextField studentSignupBirthdayDTD;
    public TextField studentSignupBirthdayMTD;
    public TextField studentSignupBirthdayYearTD;
    public TextField studentSignupPhoneNumberTF;
    public TextArea studentSignupQualificationTA;

    public Button studentSignupBtn;
    public Button studentSignupBackBtn;
    public Button studentSignupLoginBtn;


    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == studentSignupBackBtn){
            Parent root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
            mainPage.window.setTitle("College Gateway");
            mainPage.window.setScene(new Scene(root));
            mainPage.window.show();
        } else if(actionEvent.getSource() == studentSignupLoginBtn){
            Parent root = FXMLLoader.load(getClass().getResource("studentLogin.fxml"));
            mainPage.window.setTitle("Student Login");
            mainPage.window.setScene(new Scene(root));
            mainPage.window.show();
        } else if(actionEvent.getSource() == studentSignupBtn){
            String fullName = studentSignupFullNameTD.getText();
            int civilID = Integer.parseInt(studentSignupCiviIDTD.getText());
            String dOB = studentSignupBirthdayDTD.getText() + "-" + studentSignupBirthdayMTD.getText() + "-" + studentSignupBirthdayYearTD.getText();
            String phone = studentSignupPhoneNumberTF.getText();
            String email = studentSignupEmailTF.getText();
            String username = studentSignupUsernameTD.getText();
            String password = studentSignupPasswordTD.getText();
            String qualifications = studentSignupQualificationTA.getText();

            if(fullName.equals("") || dOB.equals("") || phone.equals("") || email.equals("") || qualifications.equals("") || username.equals("") || password.equals("")){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Error: All text fields must be filled to enter");
                a.show();
            } else {
                conn = DataConnection.connectDb();
                String query = "INSERT INTO `student` (`full_name`, `civil_id`, `dob`, `phone`, `email`, `username`, `password`, `qualifications`) VALUES ('" + fullName + "', '" + civilID + "', '" + dOB + "', '" + phone + "', '" + email + "', '" + username + "', '" + password + "', '" + qualifications + "')";
                try {
                    Statement statement = conn.createStatement();

                    if(statement.executeUpdate(query) == 1){
                        a.setAlertType(Alert.AlertType.CONFIRMATION);
                        a.setContentText("Your Registration Request has been sent, an admin will review your request soon. Ps: you can not login until your request has been verified");
                        a.show();
                        Parent root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
                        mainPage.window.setTitle("College Gateway");
                        mainPage.window.setScene(new Scene(root));
                        mainPage.window.show();
                    }


                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
