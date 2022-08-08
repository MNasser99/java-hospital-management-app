package oosd.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeacherBrowseCourses implements Initializable {

    Alert a = new Alert(Alert.AlertType.NONE);

    Connection conn = DataConnection.connectDb();


    public Button teacherBrowseCoursesBackBtn;
    public TextField teacherBrowseCoursesIDColumnIDTF;
    public Button teacherBrowseCoursesIDColumnViewCourseBtn;
    public TableView<TeacherBrowseCourseModelTable> teacherBrowseCoursesTable;
    public TableColumn<TeacherBrowseCourseModelTable, Integer> teacherBrowseCoursesIDColumn;
    public TableColumn<TeacherBrowseCourseModelTable, String> teacherBrowseCoursesNameColumn;
    public TableColumn<TeacherBrowseCourseModelTable, Integer> teacherBrowseCoursesCreditsColumn;

    ObservableList<TeacherBrowseCourseModelTable> oblist = FXCollections.observableArrayList();

    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == teacherBrowseCoursesBackBtn){
            Parent root = FXMLLoader.load(getClass().getResource("teacherControlPanel.fxml"));
            mainPage.window.setTitle("Teacher Control Panel");
            mainPage.window.setScene(new Scene(root));
        } else if(actionEvent.getSource() == teacherBrowseCoursesIDColumnViewCourseBtn){
            Teacher.course = Integer.parseInt(teacherBrowseCoursesIDColumnIDTF.getText());
            Parent root = FXMLLoader.load(getClass().getResource("teacherViewCourse.fxml"));
            mainPage.window.setTitle("Course Viewer");
            mainPage.window.setScene(new Scene(root));

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `course` WHERE `prof_id` = " + Teacher.id);

            while(rs.next()){
                oblist.add(new TeacherBrowseCourseModelTable(rs.getInt("course_id"), rs.getInt("credits"), rs.getString("course_name")));

            }

            teacherBrowseCoursesIDColumn.setCellValueFactory(new PropertyValueFactory<>("course_id"));
            teacherBrowseCoursesNameColumn.setCellValueFactory(new PropertyValueFactory<>("course_name"));
            teacherBrowseCoursesCreditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        teacherBrowseCoursesTable.setItems(oblist);


    }

}
