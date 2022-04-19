package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto;


import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Odontologo;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoDto {

    @Id
    private Long id;
    @JsonIgnoreProperties({"nombre","apellido","matricula", "turnos"})
    private Odontologo odontologo;
    @JsonIgnoreProperties({"nombre","apellido","email","dni", "fechaIngreso", "domicilio", "turnos"})
    private Paciente paciente;
    @Column
    private LocalDate fecha;
    @Column
    private LocalTime hora;

    public TurnoDto() {
    }

    public TurnoDto(Odontologo odontologo, Paciente paciente, LocalDate fecha, LocalTime hora) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
    }

    public TurnoDto(Long id, Odontologo odontologo, Paciente paciente, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
