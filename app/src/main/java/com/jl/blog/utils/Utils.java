package com.jl.blog.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private Utils(){
    }

    public static Date getDateNow() {
        return new Date();
    }

    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(String stringDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yy").parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
