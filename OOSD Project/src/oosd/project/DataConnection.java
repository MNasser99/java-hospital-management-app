package oosd.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class DataConnection {
    public static Connection connectDb(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =DriverManager.getConnection("jdbc:mysql://localhost/college_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
            System.out.println("Conected");
            return conn;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }

    }

    PreparedStatement prepareStatement(String query1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
