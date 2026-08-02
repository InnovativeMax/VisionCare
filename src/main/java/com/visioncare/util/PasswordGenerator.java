package com.visioncare.util;

public class PasswordGenerator {

    public static void main(String[] args) {

        String password = "Admin@123";

        String hash = PasswordUtil.hashPassword(password);

        System.out.println(hash);
    }
}