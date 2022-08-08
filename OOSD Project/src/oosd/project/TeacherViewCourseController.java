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

public class TeacherViewCourseController implements Initializable {
    Alert a = new Alert(Alert.AlertType.NONE);

    Connection conn = DataConnection.connectDb();

    public Button teacherViewCourseBackBtn;
    public TextField teacherLecturesNameTF;
    public Button teacherViewCourseAddBtn;
    public TableView<teacherCourseViewModelTable> adminStudentRegTable;
    public TableColumn<teacherCourseViewModelTable, Integer> teacherViewCourseIDColumn;
    public TableColumn<teacherCourseViewModelTable, String> teacherViewCourseTitleColumn;
    public TableColumn<teacherCourseViewModelTable, Integer> teacherViewNumberColumn;

    ObservableList<teacherCourseViewModelTable> oblist = FXCollections.observableArrayList();

    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == teacherViewCourseBackBtn){
            Parent root = FXMLLoader.load(getClass().getResource("teacherControlPanel.fxml"));
            mainPage.window.setTitle("Teacher Control Panel");
            mainPage.window.setScene(new Scene(root));
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Teacher.course);
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `lecture_names` WHERE `course_id` = " + Teacher.course);

            while(rs.next()){
                oblist.add(new teacherCourseViewModelTable(rs.getInt("id"), rs.getInt("course_id"), rs.getString("lecture_title")));

            }

            teacherViewCourseIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            teacherViewCourseTitleColumn.setCellValueFactory(new PropertyValueFactory<>("lecture_title"));
            teacherViewNumberColumn.setCellValueFactory(new PropertyValueFactory<>("course_id"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        adminStudentRegTable.setItems(oblist);
    }
}
