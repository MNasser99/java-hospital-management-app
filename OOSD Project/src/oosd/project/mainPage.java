package oosd.project;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;


public class mainPage extends Application implements EventHandler<ActionEvent> {

    // Main Scene
    Scene mainScene;
    GridPane mainPageGrid;
    Label mainPageLabel1, mainPageLabel2;
    Button adminBtn, teacherBtn, studentBtn;

    // Admin Login Scene
    Scene adminLoginScene;
    GridPane adminLoginRoot;
    Label adminLoginLabel;
    Button adminLoginBtn, adminLoginBackBtn;
    TextField adminLoginUsernameTF, adminLoginPasswordTF;

    // Teacher Login Scene
    Scene teacherLoginScene;
    GridPane teacherLoginRoot;
    Label teacherLoginLabel, teacherLoginUsernameLabel, teacherLoginPasswordLabel;
    Button teacherLoginBtn, teacherLoginBackBtn, teacherLoginSignUpBtn;
    TextField teacherLoginUsernameTF, teacherLoginPasswordTF;

    // Teacher Sign Up Scene
    Scene teacherSignUpScene;
    GridPane teacherSignUpRoot;
    Label teacherSignUpLabel, teacherSignUpUsernameLabel, teacherSignUpFullNameLabel, teacherSignUpEmailLabel, teacherSignUpPasswordLabel;
    Button teacherSignUpBtn, teacherSignUpBackBtn, teacherSignUpLoginBtn;
    TextField teacherSignUpUsernameTF, teacherSignUpFullNameTF, teacherSignUpEmailTF, teacherSignUpPasswordTF;

    // Student Login Scene
    Scene studentLoginScene;
    GridPane studentLoginRoot;
    Label studentLoginLabel, studentLoginUsernameLabel, studentLoginPasswordLabel;;
    Button studentLoginBtn, studentLoginBackBtn, studentLoginSignUpBtn;
    TextField studentLoginUsernameTF, studentLoginPasswordTF;

    // Student Sign Up Scene
    Scene studentSignUpScene;
    GridPane studentSignUpRoot;
    Label studentSignUpLabel, studentSignUpUsernameLabel, studentSignUpFullNameLabel, studentSignUpEmailLabel, studentSignUpPasswordLabel, studentSignUpLevelLabel;
    Button studentSignUpBtn, studentSignUpBackBtn, studentSignUpLoginBtn;
    TextField studentSignUpUsernameTF, studentSignUpFullNameTF, studentSignUpEmailTF, studentSignUpPasswordTF;
    ComboBox<String> studentSignUpLevelComboBox;
    
    public static Stage window;
    
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {

        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("user_select.fxml"));
        window.setTitle("College Gateway");
        window.setScene(new Scene(root, 450, 450));
        window.setResizable(false);
        window.show();
        
//
//
//        SetupMainPageElements();
//
//        SetupAdminLoginPageElements();
//        SetupAdminHomePage();
//
//        SetupTeacherLoginPageElements();
//        SetupTeacherSignUpPage();
//        SetupTeacherHomePage();
//
//        SetupStudentLoginPageElements();
//        SetupStudentSignUpPage();
//        SetupStudentHomePage();
//
//        window.setTitle("College Gateway");
//        window.setScene(mainScene);
//        window.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == adminBtn){
            window.setTitle("Admin Login");
            window.setScene(adminLoginScene);
        }
        else if(event.getSource() == teacherBtn || event.getSource() == teacherSignUpLoginBtn){
            window.setTitle("Teacher Login");
            window.setScene(teacherLoginScene);
        } else if(event.getSource() == teacherLoginSignUpBtn){
            window.setTitle("Teacher Sign Up");
            window.setScene(teacherSignUpScene);
        }
        else if(event.getSource() == studentBtn || event.getSource() == studentSignUpLoginBtn){
            window.setTitle("Student Login");
            window.setScene(studentLoginScene);
        } else if(event.getSource() == studentLoginSignUpBtn){
            window.setTitle("Student Sign Up");
            window.setScene(studentSignUpScene);
        }
        else if(event.getSource() == adminLoginBackBtn || event.getSource() == teacherLoginBackBtn || event.getSource() == studentLoginBackBtn || event.getSource() == teacherSignUpBackBtn || event.getSource() == studentSignUpBackBtn){
            window.setTitle("College Gateway");
            window.setScene(mainScene);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void SetupMainPageElements(){
        
        mainPageLabel1 = new Label("Welcome to");
        mainPageLabel2 = new Label("College Online Gateway");
        
        adminBtn = new Button();
        adminBtn.setText("Admin");
        adminBtn.setOnAction(this);
        
        teacherBtn = new Button();
        teacherBtn.setText("Teacher");
        teacherBtn.setOnAction(this);
        
        studentBtn = new Button();
        studentBtn.setText("Student");
        studentBtn.setOnAction(this);
        
        mainPageGrid = new GridPane();
        
        mainPageGrid.setAlignment(Pos.CENTER);
        mainPageGrid.setPadding(new Insets(10, 10, 10, 10));
        mainPageGrid.setHgap(5);
        mainPageGrid.setVgap(10);
        
        mainPageGrid.add(mainPageLabel1, 0, 0);
        mainPageGrid.add(mainPageLabel2, 0, 1);
        mainPageGrid.add(adminBtn, 0, 2);
        mainPageGrid.add(teacherBtn, 0, 3);
        mainPageGrid.add(studentBtn, 0, 4);
        
        mainScene = new Scene(mainPageGrid, 300, 300);
        
    }
    
    public void SetupAdminLoginPageElements(){
        adminLoginLabel = new Label("Admin Login");

        adminLoginUsernameTF = new TextField();
        adminLoginPasswordTF = new TextField();
        
        adminLoginBtn = new Button();
        adminLoginBtn.setText("Login");
        adminLoginBtn.setOnAction(this);
        
        adminLoginBackBtn = new Button();
        adminLoginBackBtn.setText("Back");
        adminLoginBackBtn.setOnAction(this);
        
        adminLoginRoot = new GridPane();
        
        adminLoginRoot.setAlignment(Pos.CENTER);
        adminLoginRoot.setPadding(new Insets(10, 10, 10, 10));
        adminLoginRoot.setHgap(10);
        adminLoginRoot.setVgap(10);
        
        adminLoginRoot.add(adminLoginLabel, 0, 0);
        adminLoginRoot.add(adminLoginUsernameTF, 0, 2);
        adminLoginRoot.add(adminLoginPasswordTF, 0, 3);
        adminLoginRoot.add(adminLoginBtn, 0, 4);
        adminLoginRoot.add(adminLoginBackBtn, 0, 7);

        adminLoginBtn.setAlignment(Pos.CENTER_RIGHT);
        
        
        adminLoginScene = new Scene(adminLoginRoot, 300, 300);
    }

    public void SetupAdminHomePage(){

    }
    
    public void SetupTeacherLoginPageElements(){
        teacherLoginLabel = new Label("Teacher Login");
        teacherLoginUsernameLabel = new Label("Username:");
        teacherLoginPasswordLabel = new Label("Password:");

        teacherLoginUsernameTF = new TextField();
        teacherLoginPasswordTF = new TextField();

        teacherLoginBtn = new Button();
        teacherLoginBtn.setText("Login");
        teacherLoginBtn.setOnAction(this);

        teacherLoginSignUpBtn = new Button();
        teacherLoginSignUpBtn.setText("Register");
        teacherLoginSignUpBtn.setOnAction(this);
        
        teacherLoginBackBtn = new Button();
        teacherLoginBackBtn.setText("Back");
        teacherLoginBackBtn.setOnAction(this);
        
        teacherLoginRoot = new GridPane();
        
        teacherLoginRoot.setAlignment(Pos.CENTER);
        teacherLoginRoot.setPadding(new Insets(10, 10, 10, 10));
        teacherLoginRoot.setHgap(10);
        teacherLoginRoot.setVgap(10);
        
        teacherLoginRoot.add(teacherLoginLabel, 1, 0);
        teacherLoginRoot.add(teacherLoginUsernameLabel, 1, 2);
        teacherLoginRoot.add(teacherLoginUsernameTF, 1, 3);
        teacherLoginRoot.add(teacherLoginPasswordLabel, 1, 4);
        teacherLoginRoot.add(teacherLoginPasswordTF, 1, 5);
        teacherLoginRoot.add(teacherLoginBtn, 1, 6);
        teacherLoginRoot.add(teacherLoginSignUpBtn, 2, 9);
        teacherLoginRoot.add(teacherLoginBackBtn, 0, 9);
        
        teacherLoginScene = new Scene(teacherLoginRoot, 300, 300);
    }

    public void SetupTeacherSignUpPage(){

        teacherSignUpLabel = new Label("Teacher Sign Up");
        teacherSignUpUsernameLabel = new Label("Username:");
        teacherSignUpFullNameLabel = new Label("Full Name:");
        teacherSignUpEmailLabel = new Label("Email:");
        teacherSignUpPasswordLabel = new Label("Password:");

        teacherSignUpUsernameTF = new TextField();
        teacherSignUpFullNameTF = new TextField();
        teacherSignUpEmailTF = new TextField();
        teacherSignUpPasswordTF = new TextField();

        teacherSignUpBtn = new Button();
        teacherSignUpBtn.setText("Sign Up");
        teacherSignUpBtn.setOnAction(this);

        teacherSignUpLoginBtn = new Button();
        teacherSignUpLoginBtn.setText("Login");
        teacherSignUpLoginBtn.setOnAction(this);

        teacherSignUpBackBtn = new Button();
        teacherSignUpBackBtn.setText("Back");
        teacherSignUpBackBtn.setOnAction(this);

        teacherSignUpRoot = new GridPane();
        teacherSignUpRoot.setAlignment(Pos.CENTER);
        teacherSignUpRoot.setPadding(new Insets(10, 10, 10, 10));
        teacherSignUpRoot.setHgap(10);
        teacherSignUpRoot.setVgap(10);

        teacherSignUpRoot.add(teacherSignUpLabel, 1, 0);
        teacherSignUpRoot.add(teacherSignUpUsernameLabel, 1, 2);
        teacherSignUpRoot.add(teacherSignUpUsernameTF, 1, 3);
        teacherSignUpRoot.add(teacherSignUpFullNameLabel, 1, 4);
        teacherSignUpRoot.add(teacherSignUpFullNameTF, 1, 5);
        teacherSignUpRoot.add(teacherSignUpEmailLabel, 1, 6);
        teacherSignUpRoot.add(teacherSignUpEmailTF, 1, 7);
        teacherSignUpRoot.add(teacherSignUpPasswordLabel, 1, 8);
        teacherSignUpRoot.add(teacherSignUpPasswordTF, 1, 9);
        teacherSignUpRoot.add(teacherSignUpBtn, 1, 10);
        teacherSignUpRoot.add(teacherSignUpLoginBtn, 2, 12);
        teacherSignUpRoot.add(teacherSignUpBackBtn, 0, 12);

        teacherSignUpScene = new Scene(teacherSignUpRoot, 300, 400);

    }

    public void SetupTeacherHomePage(){

    }
    
    public void SetupStudentLoginPageElements(){
        studentLoginLabel = new Label("Student Login");
        studentLoginUsernameLabel = new Label("Username:");
        studentLoginPasswordLabel = new Label("Password:");

        studentLoginUsernameTF = new TextField();
        studentLoginPasswordTF = new TextField();

        studentLoginBtn = new Button();
        studentLoginBtn.setText("Login");
        studentLoginBtn.setOnAction(this);

        studentLoginSignUpBtn = new Button();
        studentLoginSignUpBtn.setText("Register");
        studentLoginSignUpBtn.setOnAction(this);
        
        studentLoginBackBtn = new Button();
        studentLoginBackBtn.setText("Back");
        studentLoginBackBtn.setOnAction(this);
        
        studentLoginRoot = new GridPane();
        
        studentLoginRoot.setAlignment(Pos.CENTER);
        studentLoginRoot.setPadding(new Insets(10, 10, 10, 10));
        studentLoginRoot.setHgap(10);
        studentLoginRoot.setVgap(10);

        studentLoginRoot.add(studentLoginLabel, 1, 0);
        studentLoginRoot.add(studentLoginUsernameLabel, 1, 2);
        studentLoginRoot.add(studentLoginUsernameTF, 1, 3);
        studentLoginRoot.add(studentLoginPasswordLabel, 1, 4);
        studentLoginRoot.add(studentLoginPasswordTF, 1, 5);
        studentLoginRoot.add(studentLoginBtn, 1, 6);
        studentLoginRoot.add(studentLoginSignUpBtn, 2, 9);
        studentLoginRoot.add(studentLoginBackBtn, 0, 9);
        
        studentLoginScene = new Scene(studentLoginRoot, 300, 300);
    }

    public void SetupStudentSignUpPage(){

        studentSignUpLabel = new Label("Student Sign Up");
        studentSignUpUsernameLabel = new Label("Username:");
        studentSignUpFullNameLabel = new Label("Full Name:");
        studentSignUpEmailLabel = new Label("Email:");
        studentSignUpPasswordLabel = new Label("Password:");
        studentSignUpLevelLabel = new Label("College Level:");

        studentSignUpUsernameTF = new TextField();
        studentSignUpFullNameTF = new TextField();
        studentSignUpEmailTF = new TextField();
        studentSignUpPasswordTF = new TextField();

        studentSignUpLevelComboBox = new ComboBox<>();
        studentSignUpLevelComboBox.getItems().addAll("-Choose Level-", "Level 1", "Level 2", "Level 3", "Level 4");
        studentSignUpLevelComboBox.getSelectionModel().selectFirst();

        studentSignUpBtn = new Button();
        studentSignUpBtn.setText("Sign Up");
        studentSignUpBtn.setOnAction(this);

        studentSignUpLoginBtn = new Button();
        studentSignUpLoginBtn.setText("Login");
        studentSignUpLoginBtn.setOnAction(this);

        studentSignUpBackBtn = new Button();
        studentSignUpBackBtn.setText("Back");
        studentSignUpBackBtn.setOnAction(this);

        studentSignUpRoot = new GridPane();
        studentSignUpRoot.setAlignment(Pos.CENTER);
        studentSignUpRoot.setPadding(new Insets(10, 10, 10, 10));
        studentSignUpRoot.setHgap(10);
        studentSignUpRoot.setVgap(10);

        studentSignUpRoot.add(studentSignUpLabel, 1, 0);
        studentSignUpRoot.add(studentSignUpUsernameLabel, 1, 2);
        studentSignUpRoot.add(studentSignUpUsernameTF, 1, 3);
        studentSignUpRoot.add(studentSignUpFullNameLabel, 1, 4);
        studentSignUpRoot.add(studentSignUpFullNameTF, 1, 5);
        studentSignUpRoot.add(studentSignUpLevelLabel, 1, 6);
        studentSignUpRoot.add(studentSignUpLevelComboBox, 1, 7);
        studentSignUpRoot.add(studentSignUpEmailLabel, 1, 8);
        studentSignUpRoot.add(studentSignUpEmailTF, 1, 9);
        studentSignUpRoot.add(studentSignUpPasswordLabel, 1, 10);
        studentSignUpRoot.add(studentSignUpPasswordTF, 1, 11);
        studentSignUpRoot.add(studentSignUpBtn, 1, 13);
        studentSignUpRoot.add(studentSignUpLoginBtn, 2, 16);
        studentSignUpRoot.add(studentSignUpBackBtn, 0, 16);

        studentSignUpScene = new Scene(studentSignUpRoot, 400, 500);

    }

    public void SetupStudentHomePage(){

    }


    
}
