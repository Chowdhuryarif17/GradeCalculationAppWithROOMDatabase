package com.ariful.cgpa.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String couseName;
    public double courseGPA;
    public double courseCredit;
    public int semesterId;

    public Course(double courseGPA, double courseCredit, int semesterId) {
        this.courseGPA = courseGPA;
        this.courseCredit = courseCredit;
        this.semesterId = semesterId;
    }

    public String getCouseName() {
        return couseName;
    }

    public void setCouseName(String couseName) {
        this.couseName = couseName;
    }

    public double getCourseGPA() {
        return courseGPA;
    }

    public void setCourseGPA(double courseGPA) {
        this.courseGPA = courseGPA;
    }

    public double getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(double courseCredit) {
        this.courseCredit = courseCredit;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }
}
