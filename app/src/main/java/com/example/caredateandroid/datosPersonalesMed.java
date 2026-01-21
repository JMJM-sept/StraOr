package com.example.caredateandroid;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class datosPersonalesMed extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_datos_personales_med, container, false);

        // Obtener componentes
        EditText etNombre = view.findViewById(R.id.etNombreMed);
        EditText etApellidoPaterno = view.findViewById(R.id.etApellidoPaternoMed);
        EditText etApellidoMaterno = view.findViewById(R.id.etApellidoMaternoMed);
        EditText etCedula = view.findViewById(R.id.etCedulaMed);
        EditText etFechaNac = view.findViewById(R.id.etFechaNacMed);
        EditText etEmail = view.findViewById(R.id.etEmailMed);
        RadioGroup rgSexo = view.findViewById(R.id.rgSexoMed);
        Button btnContinuar = view.findViewById(R.id.btnContinuarMed);

        // DatePicker
        etFechaNac.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dp = new DatePickerDialog(
                    getContext(),
                    (DatePicker datePicker, int year, int month, int day) ->
                            etFechaNac.setText(day + "/" + (month + 1) + "/" + year),
                    y, m, d);
            dp.show();
        });

        btnContinuar.setOnClickListener(v -> {

            // Validaciones
            if (etNombre.getText().toString().trim().isEmpty() ||
                    etApellidoPaterno.getText().toString().trim().isEmpty() ||
                    etApellidoMaterno.getText().toString().trim().isEmpty() ||
                    etCedula.getText().toString().trim().isEmpty() ||
                    etFechaNac.getText().toString().trim().isEmpty() ||
                    etEmail.getText().toString().trim().isEmpty()) {

                Toast.makeText(getContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (rgSexo.getCheckedRadioButtonId() == -1) {
                Toast.makeText(getContext(), "Selecciona un sexo", Toast.LENGTH_SHORT).show();
                return;
            }

            // Navegar a dirección del médico
            NavHostFragment.findNavController(datosPersonalesMed.this)
                    .navigate(R.id.action_datosPersonalesMed_to_direccionMedico);
        });

        return view;
    }
}
