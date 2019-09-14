package com.example.downloadexample.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MyResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("document_path")
    @Expose
    private String document_path;

    @SerializedName("data")
    @Expose
    private ArrayList<Data> data = null;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocument_path() {
        return document_path;
    }

    public void setDocument_path(String document_path) {
        this.document_path = document_path;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data implements Serializable {


        @SerializedName("id")
        @Expose
        private String id;


        @SerializedName("homework_id")
        @Expose
        private String homework_id;


        @SerializedName("student_id")
        @Expose
        private String student_id;


        @SerializedName("date")
        @Expose
        private String date;


        @SerializedName("status")
        @Expose
        private String status;


        @SerializedName("class_id")
        @Expose
        private String class_id;

        @SerializedName("subject_name")
        @Expose
        private String subject_name;

        @SerializedName("document")
        @Expose
        private String document;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHomework_id() {
            return homework_id;
        }

        public void setHomework_id(String homework_id) {
            this.homework_id = homework_id;
        }

        public String getStudent_id() {
            return student_id;
        }

        public void setStudent_id(String student_id) {
            this.student_id = student_id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getClass_id() {
            return class_id;
        }

        public void setClass_id(String class_id) {
            this.class_id = class_id;
        }

        public String getSubject_name() {
            return subject_name;
        }

        public void setSubject_name(String subject_name) {
            this.subject_name = subject_name;
        }

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }
    }
}
