package oosd.project;

public class TeacherBrowseCourseModelTable {
    int course_id, credits;
    String course_name;

    public TeacherBrowseCourseModelTable(int course_id, int credits, String course_name) {
        this.course_id = course_id;
        this.credits = credits;
        this.course_name = course_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}