package com.example.caredateandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class tipoUsuario extends Fragment {

    private RadioGroup rgTipoUsuario;
    private RadioButton rbMedico, rbPaciente;
    private LinearLayout llCedula, llCurp;
    private EditText etCedula, etCurp;
    private Button btnContinuar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tipo_usuario, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rgTipoUsuario = view.findViewById(R.id.rgTipoUsuario);
        rbMedico = view.findViewById(R.id.rbMedico);
        rbPaciente = view.findViewById(R.id.rbPaciente);
        llCedula = view.findViewById(R.id.llCedula);
        llCurp = view.findViewById(R.id.llCurp);
        etCedula = view.findViewById(R.id.etCedula);
        etCurp = view.findViewById(R.id.etCurp);
        btnContinuar = view.findViewById(R.id.btnContinuar);

        rgTipoUsuario.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbMedico) {
                llCedula.setVisibility(View.VISIBLE);
                llCurp.setVisibility(View.GONE);
            } else if (checkedId == R.id.rbPaciente) {
                llCurp.setVisibility(View.VISIBLE);
                llCedula.setVisibility(View.GONE);
            }
        });

        btnContinuar.setOnClickListener(v -> validarYContinuar());
    }

    private void validarYContinuar() {

        int selectedId = rgTipoUsuario.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(getContext(), "Selecciona un tipo de usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        // MÉDICO
        if (selectedId == R.id.rbMedico) {
            String cedula = etCedula.getText().toString().trim();

            if (cedula.isEmpty()) {
                etCedula.setError("Ingresa tu cédula profesional");
                return;
            }

            if (!cedula.matches("\\d{7,8}")) {
                etCedula.setError("Cédula inválida");
                return;
            }

            NavHostFragment.findNavController(tipoUsuario.this)
                    .navigate(R.id.action_tipoUsuario_to_datosPersonalesMed);
            return;
        }

        // PACIENTE
        if (selectedId == R.id.rbPaciente) {

            String curp = etCurp.getText().toString().trim();

            if (curp.isEmpty()) {
                etCurp.setError("Ingresa tu CURP");
                return;
            }

            if (!curp.matches("^[A-Z]{4}[0-9]{6}[A-Z]{6}[0-9]{2}$")) {
                etCurp.setError("CURP inválida");
                return;
            }

            NavHostFragment.findNavController(tipoUsuario.this)
                    .navigate(R.id.action_tipoUsuario_to_datosPersonales);
        }
    }
}
