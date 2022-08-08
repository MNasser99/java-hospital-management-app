package oosd.project;

public class Teacher {
    static int id;
    static String full_name;
    static int course;

    static void SetVars(int _id, String _full_name){
        id = _id;
        full_name = _full_name;
    }

    static void DelVars(){
        id = -1;
        full_name = "";
        course = -1;
    }
}
