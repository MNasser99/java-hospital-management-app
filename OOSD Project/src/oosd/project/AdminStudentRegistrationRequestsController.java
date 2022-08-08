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
import java.util.ResourceBundle;

public class AdminStudentRegistrationRequestsController implements Initializable {

    Alert a = new Alert(Alert.AlertType.NONE);

    Connection conn = DataConnection.connectDb();

    public Button adminStudentRegBackBtn;
    public TextField adminStudentRegIDTF;
    public Button adminStudentRegAcceptBtn;
    public Button adminStudentRegRejectBtn;

    public TableView<AdminStudentRequestsModelTable> adminStudentRegTable;
    public TableColumn<AdminStudentRequestsModelTable, Integer> adminStudentRegIDColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminStudentRegIDColumnadminStudentRegNameColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminStudentRegCivilIDColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminStudentRegDOBColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminStudentRegPhoneColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminStudentRegEmailColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminStudentRegUsernameColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminStudentRegQualificationsColumn;

    ObservableList<AdminStudentRequestsModelTable> oblist = FXCollections.observableArrayList();




    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == adminStudentRegBackBtn){
            Admin.DelVars();
            Parent root = FXMLLoader.load(getClass().getResource("adminControlPannel.fxml"));
            mainPage.window.setTitle("Admin Control Panel");
            mainPage.window.setScene(new Scene(root));
        } else if(actionEvent.getSource() == adminStudentRegAcceptBtn){
            int id = Integer.parseInt(adminStudentRegIDTF.getText());
            conn = DataConnection.connectDb();
            String query = "UPDATE `student` SET is_valid=1 WHERE id = " + id;
            try {
                Statement statement = conn.createStatement();

                if(statement.executeUpdate(query) == 1){
                    Student.UpdateCourses();
                    a.setAlertType(Alert.AlertType.CONFIRMATION);
                    a.setContentText("The student has been accepted.");
                    a.show();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else if(actionEvent.getSource() == adminStudentRegRejectBtn){
            int id = Integer.parseInt(adminStudentRegIDTF.getText());
            conn = DataConnection.connectDb();
            String query = "DELETE FROM `student` WHERE id = " + id;
            try {
                Statement statement = conn.createStatement();

                if(statement.executeUpdate(query) == 1){
                    Student.UpdateCourses();
                    a.setAlertType(Alert.AlertType.CONFIRMATION);
                    a.setContentText("The student has been rejected.");
                    a.show();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }




    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `student` WHERE `is_valid` = 0");
            while(rs.next()){
                oblist.add(new AdminStudentRequestsModelTable(rs.getInt("id"), rs.getString("full_name"), rs.getString("civil_id"), rs.getString("dob"), rs.getString("phone"), rs.getString("email"), rs.getString("username"), rs.getString("qualifications")));

            }

            adminStudentRegIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            adminStudentRegIDColumnadminStudentRegNameColumn.setCellValueFactory(new PropertyValueFactory<>("full_name"));
            adminStudentRegCivilIDColumn.setCellValueFactory(new PropertyValueFactory<>("civil_id"));
            adminStudentRegDOBColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
            adminStudentRegPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            adminStudentRegEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            adminStudentRegUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            adminStudentRegQualificationsColumn.setCellValueFactory(new PropertyValueFactory<>("qualifications"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        adminStudentRegTable.setItems(oblist);
    }
}
