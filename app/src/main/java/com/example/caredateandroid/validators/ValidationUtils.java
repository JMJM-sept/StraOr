package com.example.caredateandroid.validators;

public class ValidationUtils {

    // Validar string no vacío
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }

    // Validar teléfono (solo dígitos y mínimo 10)
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("\\d{10}");
    }

    // Validar nombre (solo letras y espacios)
    public static boolean isValidName(String name) {
        if (name == null) return false;
        return name.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
    }
}

