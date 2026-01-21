package com.example.caredateandroid.validators;
import android.widget.EditText;
public class UserValidator {

    public static boolean validarNombre(EditText etNombre) {
        String nombre = etNombre.getText().toString();

        if (!ValidationUtils.isNotEmpty(nombre)) {
            etNombre.setError("El nombre es obligatorio");
            return false;
        }

        if (!ValidationUtils.isValidName(nombre)) {
            etNombre.setError("Ingresa un nombre válido (solo letras)");
            return false;
        }

        return true;
    }

    public static boolean validarTelefono(EditText etTelefono) {
        String telefono = etTelefono.getText().toString();

        if (!ValidationUtils.isNotEmpty(telefono)) {
            etTelefono.setError("El teléfono es obligatorio");
            return false;
        }

        if (!ValidationUtils.isValidPhone(telefono)) {
            etTelefono.setError("Debe tener 10 números");
            return false;
        }

        return true;
    }

    public static boolean validarRegistro(EditText etNombre, EditText etTelefono) {

        if (!EditTextValidator.validarNombre(etNombre)) return false;

        if (!EditTextValidator.validarTelefono(etTelefono)) return false;

        return true;
    }
}

