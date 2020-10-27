package com.ariful.cgpa.model;

import java.util.List;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Semester {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String semesterName;
    public double semesterCredit;

    public Semester(String semesterName, double semesterCredit) {
        this.semesterName = semesterName;
        this.semesterCredit = semesterCredit;
    }

    public int getSemesterId() {
        return id;
    }

    public void setSemesterId(int id) {
        this.id = id;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public double getSemesterCredit() {
        return semesterCredit;
    }

    public void setSemesterCredit(double semesterCredit) {
        this.semesterCredit = semesterCredit;
    }
}
