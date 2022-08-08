package oosd.project;

public class teacherCourseViewModelTable {
    int id, course_id;
    String lecture_title;

    public teacherCourseViewModelTable(int id, int course_id, String lecture_title) {
        this.id = id;
        this.course_id = course_id;
        this.lecture_title = lecture_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getLecture_title() {
        return lecture_title;
    }

    public void setLecture_title(String lecture_title) {
        this.lecture_title = lecture_title;
    }
}
