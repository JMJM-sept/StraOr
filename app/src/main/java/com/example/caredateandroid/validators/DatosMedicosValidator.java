package com.example.caredateandroid.validators;

import android.widget.EditText;
import android.widget.RadioGroup;
public class DatosMedicosValidator {

    public static boolean validarDatosMedicos(
            RadioGroup rgAlergias,
            EditText etAlergias,
            RadioGroup rgMedicamentos,
            EditText etMedicamentos,
            RadioGroup rgCirugias,
            EditText etCirugias
    ) {

        // Alergias
        if (rgAlergias.getCheckedRadioButtonId() == -1) {
            etAlergias.setError("Selecciona si tienes alergias");
            return false;
        }

        if (rgAlergias.getCheckedRadioButtonId() == rgAlergias.getChildAt(0).getId()) {
            if (!EditTextValidator.validarObligatorio(etAlergias, "Escribe tus alergias")) {
                return false;
            }
        }

        // Medicamentos
        if (rgMedicamentos.getCheckedRadioButtonId() == -1) {
            etMedicamentos.setError("Selecciona si tomas medicamentos");
            return false;
        }

        if (rgMedicamentos.getCheckedRadioButtonId() == rgMedicamentos.getChildAt(0).getId()) {
            if (!EditTextValidator.validarObligatorio(etMedicamentos, "Escribe tus medicamentos")) {
                return false;
            }
        }

        // Cirugías
        if (rgCirugias.getCheckedRadioButtonId() == -1) {
            etCirugias.setError("Selecciona si has tenido cirugías");
            return false;
        }

        if (rgCirugias.getCheckedRadioButtonId() == rgCirugias.getChildAt(0).getId()) {
            if (!EditTextValidator.validarObligatorio(etCirugias, "Describe tus cirugías")) {
                return false;
            }
        }

        return true;
    }
}

