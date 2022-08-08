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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class StudentCourseRegisterationController implements Initializable {

    Alert a = new Alert(Alert.AlertType.NONE);

    Connection conn = DataConnection.connectDb();

    public TableView<StudentRegisterModelTable> studentCourseRegisterationTable;
    public TableColumn<StudentRegisterModelTable, String> studentCourseRegisterationCourseIDColumn;
    public TableColumn<StudentRegisterModelTable, String> studentCourseRegisterationCourseNameColumn;
    public TableColumn<StudentRegisterModelTable, String> studentCourseRegisterationCourseProfColumn;
    public TableColumn<StudentRegisterModelTable, String> studentCourseRegisterationCreditsColumn;

    ObservableList<StudentRegisterModelTable> oblist = FXCollections.observableArrayList();

    public Button studentCourseRegisterationBackBtn;
    public TextField studentCourseRegisterationCourseIDTF;
    public Button studentCourseRegisterationRegisterBtn;

    ArrayList<Integer> coursesAvailable = new ArrayList<Integer>();


    public void handleButtonClick(ActionEvent actionEvent) throws IOException {

        if(actionEvent.getSource() == studentCourseRegisterationBackBtn){
            Parent root = FXMLLoader.load(getClass().getResource("studentControlPanel.fxml"));
            mainPage.window.setTitle("Student Control Panel");
            mainPage.window.setScene(new Scene(root));
            mainPage.window.show();
        } else if(actionEvent.getSource() == studentCourseRegisterationRegisterBtn){
            int course_id = Integer.parseInt(studentCourseRegisterationCourseIDTF.getText());
            if(coursesAvailable.contains(course_id)){
                conn = DataConnection.connectDb();
                String query = "INSERT INTO `student_course` (`student_id`, `course_id`) VALUES ('" + Student.id + "', '" + course_id + "')";
                try {
                    Statement statement = conn.createStatement();

                    if(statement.executeUpdate(query) == 1){
                        Student.UpdateCourses();
                        a.setAlertType(Alert.AlertType.CONFIRMATION);
                        a.setContentText("Your Registration Request has been sent, an admin will review your request soon.");
                        a.show();
                    }


                } catch (SQLException ex) {
                    System.out.println(ex);
                }

            } else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("The ID you inserted is not valid, please pick a valid course ID from the table.");
                a.show();
            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `course` WHERE `prof_id` != 0");
            while(rs.next()){
                int course_id = rs.getInt("course_id");
                if(Student.courses[0] != course_id && Student.courses[1] != course_id ){
                    ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM `faculty` WHERE `faculty_id` = " + rs.getInt("prof_id"));
                    if(rs2.next()){
                        oblist.add(new StudentRegisterModelTable(Integer.toString(course_id), rs.getString("course_name"), rs2.getString("full_name"), Integer.toString(rs.getInt("credits"))));
                        coursesAvailable.add(course_id);
                    }
                }
            }

            studentCourseRegisterationCourseIDColumn.setCellValueFactory(new PropertyValueFactory<>("course_id"));
            studentCourseRegisterationCourseNameColumn.setCellValueFactory(new PropertyValueFactory<>("course_name"));
            studentCourseRegisterationCourseProfColumn.setCellValueFactory(new PropertyValueFactory<>("prof"));
            studentCourseRegisterationCreditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        studentCourseRegisterationTable.setItems(oblist);


    }
}
