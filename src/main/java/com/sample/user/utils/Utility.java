package com.sample.user.utils;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Utility {

    private static final String SPACE = " ";
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static final String ID_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private static final Pattern idPattern = Pattern.compile(ID_PATTERN);

    public static boolean isValidPassword(final String password) {
        if (password.contains(SPACE) || isAllFullWidth(password)) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validateUser(String id) {
        if (isAllFullWidth(id)) {
            return false;
        }
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }

    public static boolean isAllFullWidth(String str) {
        return str.matches("[\\uff01-\\uff5E]*");
    }
}
