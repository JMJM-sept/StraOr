package com.example.caredateandroid.api;

import com.example.caredateandroid.dto.CitaDto;
import com.example.caredateandroid.dto.ClinicaDto;
import com.example.caredateandroid.dto.LoginRequest;
import com.example.caredateandroid.dto.LoginResponse;
import com.example.caredateandroid.dto.RegisterRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    // ========= REGISTRO =========
    @POST("register/paciente")
    Call<Map<String, String>> registrar(@Body RegisterRequest request);

    // ========= LOGIN ÚNICO =========
    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    // ========= CITAS =========
    @GET("Eduardo/api/cita")
    Call<List<CitaDto>> listarCitas();

    @POST("Eduardo/api/cita")
    Call<CitaDto> crearCita(@Body CitaDto dto);

    // ========= CLINICAS =========
    @GET("Eduardo/api/clinica")
    Call<List<ClinicaDto>> listarClinicas();

    @GET("Eduardo/api/clinica/{id}")
    Call<ClinicaDto> obtenerClinica(@Path("id") Integer id);

    @POST("Eduardo/api/clinica")
    Call<ClinicaDto> crearClinica(@Body ClinicaDto dto);
}
