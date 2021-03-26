package com.example.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chapter {
    private int chapterId;
    private String chapterName;
    @SerializedName("unit")
    private Unit unit;

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

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
