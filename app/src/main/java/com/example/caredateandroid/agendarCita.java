package com.example.caredateandroid;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class agendarCita extends Fragment {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText etMotivo;
    private Button btnAgendarCita;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agendar_cita, container, false);

        datePicker = view.findViewById(R.id.datePicker);
        timePicker = view.findViewById(R.id.timePicker);
        etMotivo = view.findViewById(R.id.etMotivo);
        btnAgendarCita = view.findViewById(R.id.btnAgendarCita);

        // Botón Agendar
        btnAgendarCita.setOnClickListener(v -> validarCita());

        return view;
    }

    private void validarCita() {
        String motivo = etMotivo.getText().toString().trim();

        // Validar motivo
        if (TextUtils.isEmpty(motivo)) {
            etMotivo.setError("Escribe un motivo");
            return;
        }

        // Obtener fecha
        int dia = datePicker.getDayOfMonth();
        int mes = datePicker.getMonth() + 1;
        int año = datePicker.getYear();

        // Obtener hora
        int hora = timePicker.getHour();
        int minuto = timePicker.getMinute();

        String fecha = dia + "/" + mes + "/" + año;
        String horaTxt = hora + ":" + (minuto < 10 ? "0" + minuto : minuto);

        Toast.makeText(getContext(),
                "Cita agendada:\nFecha: " + fecha + "\nHora: " + horaTxt + "\nMotivo: " + motivo,
                Toast.LENGTH_LONG).show();
    }
}
