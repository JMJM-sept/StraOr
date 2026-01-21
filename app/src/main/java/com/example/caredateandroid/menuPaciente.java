package com.example.caredateandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class menuPaciente extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_paciente, container, false);

        Button btnAgendar = view.findViewById(R.id.btnAgendarCita);
        Button btnConsultar = view.findViewById(R.id.btnConsultarCita);
        Button btnEliminar = view.findViewById(R.id.btnEliminarCita);

        // --- Navegación ---
        btnAgendar.setOnClickListener(v ->
                NavHostFragment.findNavController(menuPaciente.this)
                        .navigate(R.id.action_menuPaciente_to_agendarCita)
        );

        btnConsultar.setOnClickListener(v ->
                NavHostFragment.findNavController(menuPaciente.this)
                        .navigate(R.id.action_menuPaciente_to_consultarCita)
        );

        btnEliminar.setOnClickListener(v ->
                NavHostFragment.findNavController(menuPaciente.this)
                        .navigate(R.id.action_menuPaciente_to_cancelarCita)
        );

        return view;
    }
}
