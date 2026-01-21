package com.example.caredateandroid;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class crearCuenta extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crear_cuenta, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button siguiente = view.findViewById(R.id.btnSiguiente);

        siguiente.setOnClickListener(v ->
                NavHostFragment.findNavController(crearCuenta.this)
                        .navigate(R.id.action_crearCuenta_to_tipoUsuario)
        );
    }
}
