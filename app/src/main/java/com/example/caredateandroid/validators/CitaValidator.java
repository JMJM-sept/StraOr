package com.example.caredateandroid.validators;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
public class CitaValidator {

    public static boolean validarCita(DatePicker datePicker, TimePicker timePicker, EditText motivo) {

        // Validar motivo
        if (!EditTextValidator.validarObligatorio(motivo, "Ingresa el motivo")) {
            return false;
        }

        // Validar fecha
        if (!DateTimeValidator.validarFechaFutura(datePicker)) {
            motivo.setError("La fecha debe ser hoy o futura");
            return false;
        }

        return true;
    }
}

