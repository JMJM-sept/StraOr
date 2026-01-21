package com.example.caredateandroid.dto;

public class CitaDto {

    private Integer id;
    private String diaCita;
    private String horarioCita;
    private String motivoGeneral;
    private String notasMed;

    private Integer pacienteId;
    private Integer clinicaId;
    private Integer medicoId;

    public Integer getId() { return id; }
    public String getDiaCita() { return diaCita; }
    public String getHorarioCita() { return horarioCita; }
    public String getMotivoGeneral() { return motivoGeneral; }
    public String getNotasMed() { return notasMed; }
    public Integer getPacienteId() { return pacienteId; }
    public Integer getClinicaId() { return clinicaId; }
    public Integer getMedicoId() { return medicoId; }
}
