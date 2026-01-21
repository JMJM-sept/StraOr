package com.example.caredateandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class detalleCitaMedico extends Fragment {

    Button btnRegistrarNota;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_cita_medico, container, false);

        btnRegistrarNota = view.findViewById(R.id.btnRegistrarNotaMedica);

        btnRegistrarNota.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_detalleCitaMedico_to_registrarNotaMedica));

        return view;
    }
}
