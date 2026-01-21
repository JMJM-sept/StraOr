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

public class verCitasMedico extends Fragment {

    Button btnDetalleCita;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ver_citas_medico, container, false);

        btnDetalleCita = view.findViewById(R.id.btnDetalleCitaMedico);

        btnDetalleCita.setOnClickListener(v ->
                Navigation.findNavController(v)
                        .navigate(R.id.action_verCitasMedico_to_detalleCitaMedico));

        return view;
    }
}
