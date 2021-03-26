package com.example.test.model;

public class ListCourse {
    private int courseId;
    private String nameSubject;
    private String courseName;
    private String chairman;
    private String overView;
    private String dateStart;
    private String dateEnd;
    private String timeStart;
    private String timeEnd;
    private Boolean iCheck;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Boolean getiCheck() {
        return iCheck;
    }

    public void setiCheck(Boolean iCheck) {
        this.iCheck = iCheck;
    }

    /*
    * "      courseId": 2,
            "nameSubject": "Tiếng anh 1",
            "courseName": "Tiếng anh 1",
            "chairman": "admin",
            "overView": "Tiếng anh 1",
            "dateStart": "2021-03-04",
            "dateEnd": "2021-03-31",
            "timeStart": "08:00:00",
            "timeEnd": "10:00:00",
            "iCheck": true*/
}
