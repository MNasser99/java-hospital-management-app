package oosd.project;

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

public class StudentControlPanelController implements Initializable {
    public Label studentControlPanelLabel;

    public Button studentControlPanelViewProfileBtn;
    public Button studentControlPanelRegisterForCoursesBtn;
    public Button studentControlPanelLogoutBtn;


    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == studentControlPanelLogoutBtn){
            Student.DelVars();
            Parent root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
            mainPage.window.setTitle("College Gateway");
            mainPage.window.setScene(new Scene(root));
        } else if(actionEvent.getSource() == studentControlPanelViewProfileBtn){
            Parent root = FXMLLoader.load(getClass().getResource("studentProfile.fxml"));
            mainPage.window.setTitle("Student Profile");
            mainPage.window.setScene(new Scene(root));
        } else if(actionEvent.getSource() == studentControlPanelRegisterForCoursesBtn){
            Parent root = FXMLLoader.load(getClass().getResource("studentCourseRegisteration.fxml"));
            mainPage.window.setTitle("Course Registration");
            mainPage.window.setScene(new Scene(root));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentControlPanelLabel.setText("Welcome, " + Student.full_name);
    }
}
