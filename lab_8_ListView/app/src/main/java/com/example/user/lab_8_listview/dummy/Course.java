package com.example.user.lab_8_listview.dummy;

/**
 * Created by User on 28/03/2018.
 */

public class Course {
    private String id;
    private String subject;
    private Integer grade;


    public Course(String subject, Integer grade) {
        this.id=id;
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return(subject.toString()+" "+grade.toString());
    }
}
