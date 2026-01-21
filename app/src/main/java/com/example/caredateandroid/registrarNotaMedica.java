package com.example.caredateandroid;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class registrarNotaMedica extends Fragment {

    EditText etDiagnostico, etTratamiento, etObservaciones;
    Button btnGuardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registrar_nota_medica, container, false);

        etDiagnostico = view.findViewById(R.id.etDiagnostico);
        etTratamiento = view.findViewById(R.id.etTratamiento);
        etObservaciones = view.findViewById(R.id.etObservaciones);
        btnGuardar = view.findViewById(R.id.btnGuardarNota);

        btnGuardar.setOnClickListener(v -> guardarNota(v));

        return view;
    }

    private void guardarNota(View v) {
        String diagnostico = etDiagnostico.getText().toString().trim();
        String tratamiento = etTratamiento.getText().toString().trim();

        if (TextUtils.isEmpty(diagnostico)) {
            etDiagnostico.setError("Campo obligatorio");
            return;
        }

        if (TextUtils.isEmpty(tratamiento)) {
            etTratamiento.setError("Campo obligatorio");
            return;
        }

        Toast.makeText(getContext(), "Nota médica registrada", Toast.LENGTH_SHORT).show();

        Navigation.findNavController(v)
                .navigateUp();
    }
}
