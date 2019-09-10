package com.example.irvantest.util;

public class Util {
    public static boolean isExist(Object obj) {
        boolean isExist = false;
        if (obj != null) {
            if (obj instanceof String) {
                isExist = !((String) obj).trim().isEmpty();
            } else if (obj instanceof Integer){
                isExist = ((Integer) obj) != 0;
            }
        }
        return isExist;
    }
}