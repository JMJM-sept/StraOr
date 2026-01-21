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

public class perfilMedico extends Fragment {

    Button btnEditarPerfil;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil_m_edico, container, false);

        btnEditarPerfil = view.findViewById(R.id.btnEditarPerfilMedico);

        btnEditarPerfil.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_menuMedico_to_editarPerfilMedico));

        return view;
    }
}
