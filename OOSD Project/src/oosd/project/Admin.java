package oosd.project;

public class Admin {

    static int id;
    static String username;

    static void SetVars(int _id, String _username){
        id = _id;
        username = _username;
    }

    static void DelVars(){
        id = -1;
        username = "";
    }

}
