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

public class AdminBrowseStudentsController implements Initializable {

    public Button adminBrowseStudentsBackBtn;

    public TableView<AdminStudentRequestsModelTable> adminBrowseStudentsTable;
    public TableColumn<AdminStudentRequestsModelTable, Integer> adminBrowseStudentsIDColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminBrowseStudentsNameColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminBrowseStudentsCivilIDColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminBrowseStudentsDOBColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminBrowseStudentsPhoneColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminBrowseStudentsEmailColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminBrowseStudentsUsernameColumn;
    public TableColumn<AdminStudentRequestsModelTable, String> adminBrowseStudentsQualificationsColumn;

    Alert a = new Alert(Alert.AlertType.NONE);

    Connection conn = DataConnection.connectDb();



    ObservableList<AdminStudentRequestsModelTable> oblist = FXCollections.observableArrayList();




    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        if(actionEvent.getSource() == adminBrowseStudentsBackBtn){
            Admin.DelVars();
            Parent root = FXMLLoader.load(getClass().getResource("adminControlPannel.fxml"));
            mainPage.window.setTitle("Admin Control Panel");
            mainPage.window.setScene(new Scene(root));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `student` WHERE `is_valid` = 1");
            while(rs.next()){
                oblist.add(new AdminStudentRequestsModelTable(rs.getInt("id"), rs.getString("full_name"), rs.getString("civil_id"), rs.getString("dob"), rs.getString("phone"), rs.getString("email"), rs.getString("username"), rs.getString("qualifications")));

            }

            adminBrowseStudentsIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            adminBrowseStudentsIDColumn.setCellValueFactory(new PropertyValueFactory<>("full_name"));
            adminBrowseStudentsCivilIDColumn.setCellValueFactory(new PropertyValueFactory<>("civil_id"));
            adminBrowseStudentsDOBColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
            adminBrowseStudentsPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            adminBrowseStudentsEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            adminBrowseStudentsUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            adminBrowseStudentsQualificationsColumn.setCellValueFactory(new PropertyValueFactory<>("qualifications"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        adminBrowseStudentsTable.setItems(oblist);
    }
}
