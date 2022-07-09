package com.example.cardviewapp;

public class AppsModel {

    private String name;
    private String numOfDownloads;
    private int thumbnail;

    public AppsModel() {

    }

    public AppsModel(String name, String numOfDownloads, int thumbnail) {
        this.name = name;
        this.numOfDownloads = numOfDownloads;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumOfDownloads() {
        return numOfDownloads;
    }

    public void setNumOfDownloads(String numOfDownloads) {
        this.numOfDownloads = numOfDownloads;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
