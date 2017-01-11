package com.korobkin.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Java Developer on 27.11.2015.
 */
public class PhoneValidation {

    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("['+']?3?8?.?['(']?[\\d]{3}[')']?.?[\\d]{3}.?[\\d]{2}.?[\\d]{2}");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
