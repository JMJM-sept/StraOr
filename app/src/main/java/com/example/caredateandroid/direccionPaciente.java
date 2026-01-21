package com.example.caredateandroid;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class direccionPaciente extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_direccion_paciente, container, false);

        Button btnContinuar = view.findViewById(R.id.btnContinuarDireccionPac);

        btnContinuar.setOnClickListener(v ->
                NavHostFragment.findNavController(direccionPaciente.this)
                        .navigate(R.id.action_direccionPaciente_to_seleccionClinica)
        );

        return view;
    }
}
