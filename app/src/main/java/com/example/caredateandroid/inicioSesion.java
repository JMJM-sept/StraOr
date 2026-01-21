package com.example.caredateandroid;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caredateandroid.api.ApiClient;
import com.example.caredateandroid.api.ApiService;
import com.example.caredateandroid.dto.LoginRequest;
import com.example.caredateandroid.dto.LoginResponse;
import com.example.caredateandroid.validators.InputValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inicioSesion extends Fragment {

    private EditText correoInput, passwordInput;
    private Button btnLogin;

    public inicioSesion() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio_sesion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        correoInput = view.findViewById(R.id.correoInput);
        passwordInput = view.findViewById(R.id.passwordInput);
        btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String correo = correoInput.getText().toString().trim();
            String pass = passwordInput.getText().toString().trim();

            if (correo.isEmpty()) {
                correoInput.setError("Campo obligatorio");
                return;
            }

            if (!InputValidator.isValidEmail(correo)) {
                correoInput.setError("Correo inválido");
                return;
            }

            if (pass.isEmpty()) {
                passwordInput.setError("Campo obligatorio");
                return;
            }

            if (pass.length() < 6) {
                passwordInput.setError("Mínimo 6 caracteres");
                return;
            }

            iniciarSesion(correo, pass);
        });
    }

    private void iniciarSesion(String correo, String pass) {

        ApiService api = ApiClient.getClient().create(ApiService.class);
        LoginRequest request = new LoginRequest(correo, pass);

        api.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call,
                                   Response<LoginResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    LoginResponse r = response.body();

                    Toast.makeText(getContext(),
                            "Bienvenido " + r.getNombre(),
                            Toast.LENGTH_SHORT).show();

                    if (r.getTipo().equals("PACIENTE")) {
                        NavHostFragment.findNavController(inicioSesion.this)
                                .navigate(R.id.action_inicioSesion_to_menuPaciente);
                    } else {
                        NavHostFragment.findNavController(inicioSesion.this)
                                .navigate(R.id.action_inicioSesion_to_menuMedico);
                    }

                } else {
                    Toast.makeText(getContext(),
                            "Credenciales incorrectas",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getContext(),
                        "Error de conexión",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
