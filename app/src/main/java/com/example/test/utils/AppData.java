package com.example.test.utils;

import com.example.test.model.UserInfo;

public class AppData {

    public static UserInfo currentUser = null;

    public static boolean isLogin(){
        return currentUser != null;
    }

}
