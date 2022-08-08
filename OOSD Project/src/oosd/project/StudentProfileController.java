package oosd.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StudentProfileController implements Initializable {

    Connection conn = DataConnection.connectDb();

    Alert a = new Alert(Alert.AlertType.NONE);

    public Label studentProfileStudentIDLabel;
    public Label studentProfileFNameLabel;
    public Label studentProfileStudentCivilIDLabel;
    public Label studentProfileStudentDOBLabel;
    public Label studentProfileStudentPhoneLabel;
    public Label studentProfileStudentUsernameLabel;
    public Label studentProfileStudentEmailLabel;
    public Label studentProfileStudentQualificationsLabel;

    public Label studentProfileCourse1IDLabel;
    public Label studentProfileCourse1AttendanceLabel;
    public Label studentProfileCourse1ProfNameLabel;
    public Label studentProfileCourse1NameLabel;
    public Button studentProfileWithdrawCourse1Btn;

    public Label studentProfileCourse2IDLabel;
    public Label studentProfileCourse2NameLabel;
    public Label studentProfileCourse2ProfNameLabel;
    public Label studentProfileCourse2AttendanceLabel;
    public Button studentProfileWithdrawCourse2Btn;

    public Button studentProfileEditBtn;
    public Button studentProfileBackBtn;


    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == studentProfileBackBtn){
            Parent root = FXMLLoader.load(getClass().getResource("studentControlPanel.fxml"));
            mainPage.window.setTitle("Student Control Panel");
            mainPage.window.setScene(new Scene(root));
            mainPage.window.show();
        } else if(actionEvent.getSource() == studentProfileWithdrawCourse1Btn){
            conn = DataConnection.connectDb();
            String query = "DELETE FROM `student_course` WHERE student_id = " + Student.id + " AND course_id = " + Student.courses[0];
            try {
                Statement statement = conn.createStatement();

                if(statement.executeUpdate(query) == 1){
                    Student.UpdateCourses();
                    a.setAlertType(Alert.AlertType.CONFIRMATION);
                    a.setContentText("You have successfully withdrawn from the course.");
                    a.show();
                }

                Student.UpdateCourses();


            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else if(actionEvent.getSource() == studentProfileWithdrawCourse2Btn){
            conn = DataConnection.connectDb();
            String query = "DELETE FROM `student_course` WHERE student_id = " + Student.id + " AND course_id = " + Student.courses[1];
            try {
                Statement statement = conn.createStatement();

                if(statement.executeUpdate(query) == 1){
                    Student.UpdateCourses();
                    a.setAlertType(Alert.AlertType.CONFIRMATION);
                    a.setContentText("You have successfully withdrawn from the course.");
                    a.show();
                }
                Student.UpdateCourses();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Student.id != -1){
            // Displaying Student Info
            studentProfileStudentIDLabel.setText(studentProfileStudentIDLabel.getText() + " " + Student.id);
            studentProfileFNameLabel.setText(studentProfileFNameLabel.getText() + " " + Student.full_name);
            studentProfileStudentCivilIDLabel.setText(studentProfileStudentCivilIDLabel.getText() + " " + Student.civilID);
            studentProfileStudentDOBLabel.setText(studentProfileStudentDOBLabel.getText() + " " + Student.dOB);
            studentProfileStudentPhoneLabel.setText(studentProfileStudentPhoneLabel.getText() + " " + Student.phone);
            studentProfileStudentEmailLabel.setText(studentProfileStudentEmailLabel.getText() + " " + Student.email);
            studentProfileStudentUsernameLabel.setText(studentProfileStudentUsernameLabel.getText() + " " + Student.username);
            studentProfileStudentQualificationsLabel.setText(studentProfileStudentQualificationsLabel.getText() + " " + Student.qualifications);

            // Checking and Displaying Courses


            if(Student.courses[0] != -1){
                try {
                    ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `course` WHERE `course_id` = " + Student.courses[0]);
                    if(rs.next()){
                        int cid = rs.getInt("course_id");
                        String cname = rs.getString("course_name");
                        int prof_id = rs.getInt("prof_id");

                        ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM `faculty` WHERE `faculty_id` = " + prof_id);
                        if(rs2.next()){
                            String prof_name = rs2.getString("full_name");

                            double percentage = 0;

                            ResultSet rs3 = conn.createStatement().executeQuery("SELECT * FROM `lectures` WHERE `course_id` = " + cid + " AND `student_id` = " + Student.id);
                            if(rs3.next()){
                                double total = rs.getInt("total_lectures");
                                double attended = rs3.getInt("attended_lectures");

                                if(total > 0){
                                    percentage = attended/total;
                                } else {
                                    percentage = 0;
                                }
                            }

                            studentProfileCourse1IDLabel.setText(Integer.toString(cid));
                            studentProfileCourse1NameLabel.setText(cname);
                            studentProfileCourse1ProfNameLabel.setText(prof_name);
                            studentProfileCourse1AttendanceLabel.setText(Double.toString(percentage) + "%");


                        }

                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if(Student.courses[1] != -1){
                    try {
                        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `course` WHERE `course_id` = " + Student.courses[1]);
                        if(rs.next()){
                            int cid = rs.getInt("course_id");
                            String cname = rs.getString("course_name");
                            int prof_id = rs.getInt("prof_id");

                            ResultSet rs2 = conn.createStatement().executeQuery("SELECT * FROM `faculty` WHERE `faculty_id` = " + prof_id);
                            if(rs2.next()){
                                String prof_name = rs2.getString("full_name");

                                double percentage = 0;

                                ResultSet rs3 = conn.createStatement().executeQuery("SELECT * FROM `lectures` WHERE `course_id` = " + cid + " AND `student_id` = " + Student.id);
                                if(rs3.next()){
                                    double total = rs.getInt("total_lectures");
                                    double attended = rs3.getInt("attended_lectures");

                                    if(total > 0){
                                        percentage = attended/total;
                                    } else {
                                        percentage = 0;
                                    }
                                }

                                studentProfileCourse2IDLabel.setText(Integer.toString(cid));
                                studentProfileCourse2NameLabel.setText(cname);
                                studentProfileCourse2ProfNameLabel.setText(prof_name);
                                studentProfileCourse2AttendanceLabel.setText(Double.toString(percentage) + "%");
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    studentProfileCourse2IDLabel.setVisible(false);
                    studentProfileCourse2NameLabel.setVisible(false);
                    studentProfileCourse2ProfNameLabel.setVisible(false);
                    studentProfileCourse2AttendanceLabel.setVisible(false);
                    studentProfileWithdrawCourse2Btn.setVisible(false);
                }
            } else {
                studentProfileCourse1IDLabel.setVisible(false);
                studentProfileCourse1AttendanceLabel.setVisible(false);
                studentProfileCourse1ProfNameLabel.setVisible(false);
                studentProfileCourse1NameLabel.setVisible(false);
                studentProfileWithdrawCourse1Btn.setVisible(false);

                studentProfileCourse2IDLabel.setVisible(false);
                studentProfileCourse2NameLabel.setVisible(false);
                studentProfileCourse2ProfNameLabel.setVisible(false);
                studentProfileCourse2AttendanceLabel.setVisible(false);
                studentProfileWithdrawCourse2Btn.setVisible(false);
            }

        } else {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainPage.window.setTitle("College Gateway");
            mainPage.window.setScene(new Scene(root));
            mainPage.window.show();
        }
    }
}
