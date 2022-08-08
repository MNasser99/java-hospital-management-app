package oosd.project;

import com.sun.org.omg.CORBA.Initializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminControlPanelController implements Initializable {
    public Label adminControlPanelWelcomeLabel;

    public Button adminControlPanelStudentRegBtn;
    public Button adminControlPanelStudenCourseBtn;
    public Button adminControlPanelBrowseCoursesBtn;
    public Button adminControlPanelBrowseStudentsBtn;
    public Button adminControlPanelBrowseFacultyMembersBtn;
    public Button adminControlPanelLogoutBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adminControlPanelWelcomeLabel.setText("Welcome, " + Admin.username);
    }

    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == adminControlPanelLogoutBtn){
            Admin.DelVars();
            Parent root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
            mainPage.window.setTitle("College Gateway");
            mainPage.window.setScene(new Scene(root));
        } else if(actionEvent.getSource() == adminControlPanelStudentRegBtn){
            Admin.DelVars();
            Parent root = FXMLLoader.load(getClass().getResource("adminStudentRegistrationRequests.fxml"));
            mainPage.window.setTitle("Student Registration Requests");
            mainPage.window.setScene(new Scene(root));
        }else if(actionEvent.getSource() == adminControlPanelBrowseStudentsBtn){
            Admin.DelVars();
            Parent root = FXMLLoader.load(getClass().getResource("adminBrowseStudents.fxml"));
            mainPage.window.setTitle("Browse Students");
            mainPage.window.setScene(new Scene(root));
        }





    }
}
