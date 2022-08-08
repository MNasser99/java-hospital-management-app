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

public class TeacherControlPanelController implements Initializable {

    public Button teacherControlPanelViewCoursesBtn;
    public Button teacherControlPanelBrowseMsgsBtn;
    public Button teacherControlPanelLogoutBtn;
    public Label teacherControlPanelLabel;

    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == teacherControlPanelLogoutBtn){
            Teacher.DelVars();
            Parent root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
            mainPage.window.setTitle("College Gateway");
            mainPage.window.setScene(new Scene(root));
        } else if(actionEvent.getSource() == teacherControlPanelViewCoursesBtn){
            Parent root = FXMLLoader.load(getClass().getResource("teacherBrowseCourses.fxml"));
            mainPage.window.setTitle("Browse Courses");
            mainPage.window.setScene(new Scene(root));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teacherControlPanelLabel.setText("Welcome, Dr." + Teacher.full_name);
        System.out.println(Teacher.id);
    }
}
