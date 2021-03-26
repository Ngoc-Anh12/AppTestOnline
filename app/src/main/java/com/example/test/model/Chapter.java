package com.example.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chapter {
    private int chapterId;
    private String chapterName;
    @SerializedName("unit")
    private List<Unit> unit;

    public List<Unit> getUnit() {
        return unit;
    }

    public void setUnit(List<Unit> unit) {
        this.unit = unit;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }


}
