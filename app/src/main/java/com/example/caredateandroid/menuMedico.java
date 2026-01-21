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

public class menuMedico extends Fragment {

    Button btnVerCitas, btnPerfil;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_medico, container, false);

        btnVerCitas = view.findViewById(R.id.btnVerCitasMedico);
        btnPerfil = view.findViewById(R.id.btnPerfilMedico);

        btnVerCitas.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_menuMedico_to_verCitasMedico));

        btnPerfil.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_menuMedico_to_perfilMedico));

        return view;
    }
}
