package com.example.caredateandroid;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class direccionMedico extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_direccion_medico, container, false);

        EditText etCalle = view.findViewById(R.id.etCalle);
        EditText etNumero = view.findViewById(R.id.etNumero);
        EditText etColonia = view.findViewById(R.id.etColonia);
        EditText etMunicipio = view.findViewById(R.id.etMunicipio);
        EditText etCP = view.findViewById(R.id.etCP);
        Button btnSiguiente = view.findViewById(R.id.btnSiguienteDM);

        btnSiguiente.setOnClickListener(v -> {

            if (etCalle.getText().toString().trim().isEmpty() ||
                    etNumero.getText().toString().trim().isEmpty() ||
                    etColonia.getText().toString().trim().isEmpty() ||
                    etMunicipio.getText().toString().trim().isEmpty() ||
                    etCP.getText().toString().trim().isEmpty()) {

                Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }


            NavHostFragment.findNavController(direccionMedico.this)
                    .navigate(R.id.action_direccionMedico_to_menuMedico);
        });

        return view;
    }
}
