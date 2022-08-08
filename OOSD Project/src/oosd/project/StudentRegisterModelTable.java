package oosd.project;

public class StudentRegisterModelTable {
    String course_id, credits;
    String course_name, prof;

    public StudentRegisterModelTable(String course_id, String credits, String course_name, String prof) {
        this.course_id = course_id;
        this.credits = credits;
        this.course_name = course_name;
        this.prof = prof;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }
}
