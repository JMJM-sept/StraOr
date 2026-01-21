package com.example.caredateandroid;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class consultarCita extends Fragment {

    private TextView tvCita;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_consultar_cita, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCita = view.findViewById(R.id.tvCita);

        validarCitaGuardada();
    }

    private void validarCitaGuardada() {

        // Ejemplo simulando BD (después lo conectas)
        String citaGuardada = "28/11/2025 - 14:30 - General";

        if (citaGuardada == null || citaGuardada.equals("")) {
            Toast.makeText(getContext(), "No tienes citas registradas", Toast.LENGTH_SHORT).show();
            tvCita.setText("No hay citas registradas");
            return;
        }

        tvCita.setText(citaGuardada);
    }
}
