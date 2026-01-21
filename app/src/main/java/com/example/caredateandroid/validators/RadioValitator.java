package com.example.caredateandroid.validators;

import android.widget.RadioGroup;

public class RadioValitator {

    public static boolean validarSeleccion(RadioGroup group, String mensaje) {
        if (group.getCheckedRadioButtonId() == -1) {
            return false;
        }
        return true;
    }
}

