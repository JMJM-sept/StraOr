package com.example.caredateandroid;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.caredateandroid.validators.DatosMedicosValidator;

public class datosMedicos extends Fragment {

    private RadioGroup rgAlergias, rgMedicamentos, rgCirugias, rgEnfermedades;
    private EditText etAlergias, etMedicamentos, etCirugias, etEnfermedades;
    private Spinner spinnerTipoSangre;
    private Button btnContinuarDM;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_datos_medicos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // RadioGroups
        rgAlergias       = view.findViewById(R.id.rgAlergias);
        rgMedicamentos   = view.findViewById(R.id.rgMedicamentos);
        rgCirugias       = view.findViewById(R.id.rgCirugias);
        rgEnfermedades   = view.findViewById(R.id.rgEnfermedades);

        // EditTexts
        etAlergias       = view.findViewById(R.id.etAlergias);
        etMedicamentos   = view.findViewById(R.id.etMedicamentos);
        etCirugias       = view.findViewById(R.id.etCirugias);
        etEnfermedades   = view.findViewById(R.id.etEnfermedades);

        // Spinner
        spinnerTipoSangre = view.findViewById(R.id.spinnerTipoSangre);
        cargarTiposDeSangre();

        // Botón
        btnContinuarDM = view.findViewById(R.id.btnContinuarDM);
        btnContinuarDM.setOnClickListener(v -> validarYContinuar());
    }

    private void cargarTiposDeSangre() {
        String[] tipos = {"Selecciona", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                tipos
        );
        spinnerTipoSangre.setAdapter(adapter);
    }

    private void validarYContinuar() {

        // Validación general de radio + edittext
        boolean ok = DatosMedicosValidator.validarDatosMedicos(
                rgAlergias, etAlergias,
                rgMedicamentos, etMedicamentos,
                rgCirugias, etCirugias
        );

        if (!ok) return;

        // Validar tipo de sangre
        if (spinnerTipoSangre.getSelectedItem().toString().equals("Selecciona")) {
            Toast.makeText(getContext(), "Selecciona un tipo de sangre", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar enfermedades crónicas
        if (rgEnfermedades.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "Selecciona si tienes enfermedades crónicas", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rgEnfermedades.getCheckedRadioButtonId() == R.id.rbEnfermedadSi &&
                etEnfermedades.getText().toString().trim().isEmpty()) {

            etEnfermedades.setError("Indica cuáles");
            etEnfermedades.requestFocus();
            return;
        }

        // SI TODO ESTÁ BIEN → NAVEGAR
        NavHostFragment.findNavController(datosMedicos.this)
                .navigate(R.id.action_datosMedicos_to_direccionPaciente);
    }
}
