package com.example.caredateandroid;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class seleccionClinica extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seleccion_clinica, container, false);

        Button btnConfirmar = view.findViewById(R.id.btnConfirmarClinica);

        btnConfirmar.setOnClickListener(v ->
                NavHostFragment.findNavController(seleccionClinica.this)
                        .navigate(R.id.action_seleccionClinica_to_menuPaciente)
        );

        return view;
    }
}
