package com.example.caredateandroid.validators;

import android.widget.EditText;
public class EditTextValidator {

    public static boolean validarObligatorio(EditText et, String mensaje) {
        String texto = et.getText().toString().trim();

        if (texto.isEmpty()) {
            et.setError(mensaje);
            et.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean validarNombre(EditText et) {
        String texto = et.getText().toString().trim();

        if (!ValidationUtils.isNotEmpty(texto)) {
            et.setError("El nombre es obligatorio");
            return false;
        }
        if (!ValidationUtils.isValidName(texto)) {
            et.setError("Sólo letras y espacios");
            return false;
        }
        return true;
    }

    public static boolean validarTelefono(EditText et) {
        String texto = et.getText().toString().trim();

        if (!ValidationUtils.isNotEmpty(texto)) {
            et.setError("El teléfono es obligatorio");
            return false;
        }
        if (!ValidationUtils.isValidPhone(texto)) {
            et.setError("Debe tener 10 números");
            return false;
        }
        return true;
    }
}

