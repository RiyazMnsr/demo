package com.example.demo.controller;

import com.google.gson.Gson;

public final class ControllerTestHelper {

    private static Gson gson = new Gson();

    public static String convertObjectToString(Object object) {
        return gson.toJson(object);
    }
}
