package com.example.mydhakaproject.Models;

public class SimModel {
    private String operator;
    private String title;
    private String subTitle;
    private String code;


    public SimModel() {
    }

    public SimModel(String operator, String title, String subTitle, String code) {
        this.operator = operator;
        this.title = title;
        this.subTitle = subTitle;
        this.code = code;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SimModel{" +
                "operator='" + operator + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
