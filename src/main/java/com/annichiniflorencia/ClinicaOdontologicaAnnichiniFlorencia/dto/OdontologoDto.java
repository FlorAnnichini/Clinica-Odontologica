package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto;

public class OdontologoDto {

    private Long id;
    private String apellido;
    private String nombre;
    private String matricula;

    public OdontologoDto() {
    }

    public OdontologoDto(String apellido, String nombre, String matricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public OdontologoDto(Long id, String apellido, String nombre, String matricula) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
