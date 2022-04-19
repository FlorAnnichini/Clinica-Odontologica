package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.PacienteDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Domicilio;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;


class PacienteServiceTest {

    PacienteService pacienteService;

    @Test
    void listarTodosLosPacientesTest() {
        //Dado

        List<PacienteDto> pacientes = pacienteService.listarPacientes();
        //Cuando
        Assert.assertTrue(!pacientes.isEmpty());
        Assert.assertTrue(pacientes.size() > 0);

    }

    @Test
    public void guardarPacienteTest() throws BadRequestException, ResourceNotFoundException {
        Domicilio domicilio = new Domicilio("Las Lilas", 123, "San Cristobal", "Santa Fe");
        LocalDate fecha = LocalDate.now();
        PacienteDto pacienteDto = pacienteService.guardar(new PacienteDto("Diaz", "Tomas", "12345678","tomas@gmail.com", fecha, domicilio));
        Assert.assertNotNull(pacienteService.buscarPorId(pacienteDto.getId()));
    }

    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        pacienteService.eliminar(1L);
        Assert.assertTrue(pacienteService.buscarPorId(1L) == null);
    }

    @Test
    void buscarPorIdPacienteTest() throws ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.buscarPorId(1L);
        Assert.assertTrue(pacienteDto.getId() == 1L);
    }

    @Test
    void actualizarPacienteTest() throws ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.buscarPorId(1L);
        pacienteDto.setApellido("Perez");
        pacienteDto.setNombre("Juan");
        pacienteDto.setDni("18415");
        pacienteService.actualizar(pacienteDto);
        Assert.assertTrue(pacienteService.buscarPorId(1L).getNombre().equals("Juan"));
        Assert.assertTrue(pacienteService.buscarPorId(1L).getApellido().equals("Perez"));
        Assert.assertTrue(pacienteService.buscarPorId(1L).getDni().equals("18415"));
    }
}