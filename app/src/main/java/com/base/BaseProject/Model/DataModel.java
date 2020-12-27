package com.base.BaseProject.Model;

public class DataModel {

    public String cardHeading;
    public int totalCourses;
    public int completedCourses;
    public String color;

    public DataModel(String cardHeading, int totalCourses, int completedCourses, String color) {
        this.cardHeading = cardHeading;
        this.totalCourses = totalCourses;
        this.completedCourses = completedCourses;
        this.color = color;
    }
}