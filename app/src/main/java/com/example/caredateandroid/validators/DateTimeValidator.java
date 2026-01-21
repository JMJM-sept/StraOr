package com.example.caredateandroid.validators;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.Calendar;

public class DateTimeValidator {

    public static boolean validarFechaFutura(DatePicker dp) {
        Calendar hoy = Calendar.getInstance();
        Calendar seleccionada = Calendar.getInstance();

        seleccionada.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());

        return !seleccionada.before(hoy);
    }

    public static String obtenerHora(TimePicker timePicker) {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        return hour + ":" + String.format("%02d", minute);
    }

    public static String obtenerFecha(DatePicker dp) {
        return dp.getDayOfMonth() + "/" + (dp.getMonth() + 1) + "/" + dp.getYear();
    }
}

