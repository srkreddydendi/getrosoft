package com.getrosoft.getrosoft.util;

public class AppUtil {

    public static final String CHAR_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int CHAR_SET_LEN = CHAR_SET.length();

    public static String compress(long number) {
        StringBuilder result = new StringBuilder();
        while (number > 0) {
            int remainder = (int) (number % CHAR_SET_LEN);
            result.append(CHAR_SET.charAt(remainder));
            number /= CHAR_SET_LEN;
        }
        return result.toString().toUpperCase();
    }
}
