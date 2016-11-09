package com.mayankattri.mc_assignment_4;

/**
 * Created by mayank on 1/10/16.
 */
public class Note {
    private int id;
    private int status;
    private String title;
    private String detail;
    private String date;

    public Note() {
    }

    public Note(int id, int status, String title, String detail, String date) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.status = status;
    }

    public Note(int status, String title, String detail, String date) {
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.status = status;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getID() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getDate() {
        return date;
    }
}
