package com.youcode.rentalhive.helper;

import java.util.Date;

public class ValidationHelper {

    public static String getCurrentDate() {
        Date today = new Date();
        return today.toString();
    }

    public boolean isToday(String date) {
        Date today = new Date();
        return today.toString().equals(date);
    }

    public static boolean isNumber(String quantity) throws NumberFormatException {
        // Attempt to parse the quantity as an integer
        Integer.parseInt(quantity);
        return true; // If successful, it's a number
    }
}
