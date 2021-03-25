package com.example.test.model;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    private int userId;

    @SerializedName("fullname")
    private String fullName;

    private String address;

    @SerializedName("numberphone")
    private String numberPhone;

    private String dob;

    private int sex;

    private String avatar;

    private String createAt;

    private String email;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
    *   userId: number;
     fullname: string;
     address: string;
     numberphone: string;
     dob: string;
     sex: number;
     avatar: string;
     createAt: string;
     email: string;*/
}
