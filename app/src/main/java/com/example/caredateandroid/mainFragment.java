package com.example.caredateandroid;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class mainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflamos el layout
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Referencias a botones
        Button crearCuenta = view.findViewById(R.id.ccuenta);
        Button iniciarSesion = view.findViewById(R.id.isesion);

        // Navegar a Crear Cuenta
        crearCuenta.setOnClickListener(v ->
                NavHostFragment.findNavController(mainFragment.this)
                        .navigate(R.id.action_mainFragment_to_crearCuenta)
        );

        // Navegar a Inicio Sesión
        iniciarSesion.setOnClickListener(v ->
                NavHostFragment.findNavController(mainFragment.this)
                        .navigate(R.id.action_mainFragment_to_inicioSesion)
        );



        return view;
    }
}
