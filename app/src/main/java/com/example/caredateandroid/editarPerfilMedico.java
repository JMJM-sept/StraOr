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

public class editarPerfilMedico extends Fragment {

    EditText etNombre, etTelefono, etDireccion;
    Button btnGuardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editar_perfil_medico, container, false);

        etNombre = view.findViewById(R.id.etNombreMedico);
        etTelefono = view.findViewById(R.id.etTelefonoMedico);
        etDireccion = view.findViewById(R.id.etDireccionMedico);
        btnGuardar = view.findViewById(R.id.btnGuardarPerfilMedico);

        btnGuardar.setOnClickListener(v -> guardarPerfil(v));

        return view;
    }

    private void guardarPerfil(View v) {
        if (TextUtils.isEmpty(etNombre.getText())) {
            etNombre.setError("Campo obligatorio");
            return;
        }

        if (TextUtils.isEmpty(etTelefono.getText())) {
            etTelefono.setError("Campo obligatorio");
            return;
        }

        Toast.makeText(getContext(), "Perfil actualizado", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(v).navigateUp();
    }
}
