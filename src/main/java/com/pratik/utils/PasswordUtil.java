package com.pratik.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    private static PasswordEncoder pe = new BCryptPasswordEncoder();

    public static String encode(String unencriptedPassword){
        return pe.encode(unencriptedPassword);
    }

}
