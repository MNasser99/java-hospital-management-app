package oosd.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
    static Connection conn = DataConnection.connectDb();

    static int id;
    static String full_name;
    static String civilID;
    static String dOB;
    static String phone;
    static String email;
    static String username;
    static String qualifications;

    static int[] courses = {-1, -1};

    static void SetVars(int _id, String _full_name, String _civilID, String _dOB, String _phone, String _email, String _username, String _qualifications){
        id = _id;
        full_name = _full_name;
        civilID = _civilID;
        dOB = _dOB;
        phone = _phone;
        email = _email;
        username = _username;
        qualifications = _qualifications;
    }

    static void SetVars(int _id, String _full_name, String _civilID, String _dOB, String _phone, String _email, String _username, String _qualifications, int course1){
        id = _id;
        full_name = _full_name;
        civilID = _civilID;
        dOB = _dOB;
        phone = _phone;
        email = _email;
        username = _username;
        qualifications = _qualifications;

        courses[0] = course1;
    }

    static void SetVars(int _id, String _full_name, String _civilID, String _dOB, String _phone, String _email, String _username, String _qualifications, int course1, int course2){
        id = _id;
        full_name = _full_name;
        civilID = _civilID;
        dOB = _dOB;
        phone = _phone;
        email = _email;
        username = _username;
        qualifications = _qualifications;

        courses[0] = course1;
        courses[1] = course2;
    }

    static void UpdateCourses() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM `student_course` WHERE `student_id` = " + id + " AND approved = 1");
        if(rs.next()){
            courses[0] = rs.getInt("course_id");
        }
        if(rs.next()){
            courses[1] = rs.getInt("course_id");
        }
        System.out.println(courses[0]);
        System.out.println(courses[1]);
    }

    static void DelVars(){
        id = -1;
        full_name = "";
        civilID = "";
        dOB = "";
        phone = "";
        email = "";
        username = "";
        qualifications = "";

    }
}
