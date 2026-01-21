package com.example.caredateandroid.utils;

import android.text.TextUtils;
import java.util.regex.Pattern;

public class Validators {

    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern NOMBRE = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s'-]{2,50}$");
    private static final Pattern TELEFONO = Pattern.compile("^[0-9]{7,15}$");
    private static final Pattern TELEFONO_MX = Pattern.compile("^[0-9]{10}$");
    private static final Pattern CURP = Pattern.compile("^[A-Z]{4}\\d{6}[HM][A-Z]{5}[A-Z0-9]\\d$");
    private static final Pattern DATE_DDMMYYYY = Pattern.compile("^([0-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$");
    private static final Pattern HORA_HHMM = Pattern.compile("^([01][0-9]|2[0-3]):[0-5][0-9]$");

    public static boolean isRequired(String s) {
        return !TextUtils.isEmpty(s);
    }

    public static boolean isEmail(String s) {
        return !TextUtils.isEmpty(s) && EMAIL.matcher(s).matches();
    }

    public static boolean isName(String s) {
        return !TextUtils.isEmpty(s) && NOMBRE.matcher(s).matches();
    }

    public static boolean isPhone(String s) {
        return !TextUtils.isEmpty(s) && TELEFONO.matcher(s).matches();
    }

    public static boolean isPhoneMX(String s) {
        return !TextUtils.isEmpty(s) && TELEFONO_MX.matcher(s).matches();
    }

    public static boolean isCURP(String s) {
        return !TextUtils.isEmpty(s) && CURP.matcher(s).matches();
    }

    public static boolean isDateDDMMYYYY(String s) {
        return !TextUtils.isEmpty(s) && DATE_DDMMYYYY.matcher(s).matches();
    }

    public static boolean isHourHHmm(String s) {
        return !TextUtils.isEmpty(s) && HORA_HHMM.matcher(s).matches();
    }

    public static boolean isPasswordStrong(String s) {
        if (TextUtils.isEmpty(s)) return false;
        if (s.length() < 8) return false;
        boolean hasDigit = s.matches(".*\\d.*");
        boolean hasLetter = s.matches(".*[A-Za-z].*");
        return hasDigit && hasLetter;
    }
}
