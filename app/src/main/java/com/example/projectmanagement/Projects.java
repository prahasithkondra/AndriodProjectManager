package com.example.projectmanagement;

import java.util.ArrayList;

public class Projects  {

    private String projectname;
    private String coursename;
    private String duedate;
    private int id;

    public Projects(int id, String projectname1, String coursename1, String duedate1){
        projectname = projectname1;
        coursename = coursename1;
        duedate = duedate1;
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getDuedate() {
        return duedate;
    }
    public int getId(){return this.id;}
}
