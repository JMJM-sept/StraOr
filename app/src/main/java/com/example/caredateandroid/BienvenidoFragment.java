package com.example.caredateandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.button.MaterialButton;

public class BienvenidoFragment extends Fragment {

    public BienvenidoFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bienvenido, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnIniciarSesion = view.findViewById(R.id.btnIniciarSesion);
        MaterialButton btnCrearCuenta = view.findViewById(R.id.btnCrearCuenta);

        btnIniciarSesion.setOnClickListener(v ->
                NavHostFragment.findNavController(BienvenidoFragment.this)
                        .navigate(R.id.action_mainFragment_to_inicioSesion)
        );

        btnCrearCuenta.setOnClickListener(v ->
                NavHostFragment.findNavController(BienvenidoFragment.this)
                        .navigate(R.id.action_mainFragment_to_crearCuenta)
        );
    }
}
