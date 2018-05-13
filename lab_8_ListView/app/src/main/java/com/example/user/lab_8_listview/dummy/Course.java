package com.example.user.lab_8_listview.dummy;

/**
 * Created by User on 28/03/2018.
 */

public class Course {
    private Integer id;
    private String subject;
    private Double grade;


    public Course(String subject, Double grade) {
        this.id=id;
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return(subject+" "+grade.toString());
    }
}
