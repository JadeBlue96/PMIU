package com.example.user.lab_11_12_sqlite.model;

public class Footballer {
    private long foot_id;
    private String first_name;
    private String last_name;
    private String gender;
    private String team_name;
    private Integer career_goals;

    public Footballer(long foot_id, String first_name, String last_name, String gender, String team_name, Integer career_goals) {
        this.foot_id = foot_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.team_name = team_name;
        this.career_goals = career_goals;
    }
    public Footballer(){this.career_goals = 0;}

    public long getFoot_id() {
        return foot_id;
    }

    public void setFoot_id(long foot_id) {
        this.foot_id = foot_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Integer getCareer_goals() {
        return career_goals;
    }

    public void setCareer_goals(Integer career_goals) {
        this.career_goals = career_goals;
    }

    @Override
    public String toString() {
        return "Footballer{" +
                "ID:" + foot_id +
                ", First Name:'" + first_name + '\'' +
                ", Last Name:'" + last_name + '\'' +
                ", Gender:'" + gender + '\'' +
                ", Team Name:'" + team_name + '\'' +
                ", Career goals:" + career_goals +
                '}';
    }
}
