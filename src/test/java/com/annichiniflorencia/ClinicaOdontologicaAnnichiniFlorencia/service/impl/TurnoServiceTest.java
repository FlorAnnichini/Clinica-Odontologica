package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.OdontologoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.PacienteDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.TurnoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Domicilio;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Odontologo;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Paciente;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


class TurnoServiceTest {

    TurnoService turnoService;
    OdontologoService odontologoService;
    PacienteService pacienteService;

    @Test
    void listarTodosLosTurnosTest() {
        //Dado
        List<TurnoDto> turnos = turnoService.listarTurnos();
        //Cuando
        Assert.assertTrue(!turnos.isEmpty());
        Assert.assertTrue(turnos.size() > 0);

    }

    @Test
    public void guardarTurnoTest() throws BadRequestException, ResourceNotFoundException {
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        TurnoDto turnoDto = turnoService.guardar(new TurnoDto(odontologo, paciente, fecha, hora));
        Assert.assertNotNull(turnoService.buscarPorId(turnoDto.getId()));
    }

    @Test
    public void eliminarTurnoTest() throws ResourceNotFoundException {
        turnoService.eliminar(1L);
        Assert.assertTrue(turnoService.buscarPorId(1L) == null);
    }

    @Test
    void buscarPorIdTurnoTest() throws ResourceNotFoundException {
        TurnoDto turnoDto = turnoService.buscarPorId(1L);
        Assert.assertTrue(turnoDto.getId() == 1L);
    }

    @Test
    void actualizarTurnoTest() throws ResourceNotFoundException {
        TurnoDto turnoDto = turnoService.buscarPorId(1L);
        turnoDto.setFecha(LocalDate.now());
        turnoDto.setHora( LocalTime.now());
        turnoService.actualizar(turnoDto);
        Assert.assertTrue(turnoService.buscarPorId(1L).getFecha().equals(LocalDate.now()));
        Assert.assertTrue(turnoService.buscarPorId(1L).getHora().equals(LocalTime.now()));
    }
}