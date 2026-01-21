package com.example.caredateandroid;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;

import com.example.caredateandroid.validators.EditTextValidator;

import java.util.Calendar;

public class datosPersonales extends Fragment {

    private EditText etNombre, etApellido, etFechaNac;
    private Button btnContinuarDP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_datos_personales, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNombre = view.findViewById(R.id.etNombre);
        etApellido = view.findViewById(R.id.etApellido);
        etFechaNac = view.findViewById(R.id.etFechaNac);
        btnContinuarDP = view.findViewById(R.id.btnContinuarDP);

        etFechaNac.setOnClickListener(v -> mostrarDatePicker());

        btnContinuarDP.setOnClickListener(v -> validarYContinuar());
    }

    private void mostrarDatePicker() {
        final Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                getContext(),
                (DatePicker view, int year, int month, int day) ->
                        etFechaNac.setText(day + "/" + (month + 1) + "/" + year),
                y, m, d
        );

        dialog.show();
    }

    private void validarYContinuar() {

        if (!EditTextValidator.validarNombre(etNombre)) return;
        if (!EditTextValidator.validarNombre(etApellido)) return;

        if (etFechaNac.getText().toString().trim().isEmpty()) {
            etFechaNac.setError("Selecciona tu fecha de nacimiento");
            return;
        }

        NavHostFragment.findNavController(datosPersonales.this)
                .navigate(R.id.action_datosPersonales_to_datosMedicos);
    }
}
