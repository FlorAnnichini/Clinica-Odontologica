package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.OdontologoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class OdontologoServiceTest {

    OdontologoService odontologoService;

    @Test
    void listarTodosLosOdontologosTest() {
        //Dado
        List<OdontologoDto> odontologos = odontologoService.listarOdontologos();
        //Cuando
        Assert.assertTrue(!odontologos.isEmpty());
        Assert.assertTrue(odontologos.size() > 0);

    }

   @Test
   public void guardarOdontologoTest() throws BadRequestException {
        OdontologoDto odontologo = odontologoService.guardar(new OdontologoDto("Flor", "Ramirez", "123tre"));
        Assert.assertTrue(odontologo.getId() != null);
    }

    @Test
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        odontologoService.eliminar(1L);
        Assert.assertTrue(odontologoService.buscarPorId(1L) == null);
    }

    @Test
    void buscarPorIdOdontologoTest() throws ResourceNotFoundException {
        OdontologoDto odontologoDto = odontologoService.buscarPorId(1L);
        Assert.assertTrue(odontologoDto.getId() == 1);
    }

    @Test
    void actualizarOdontologoTest() throws ResourceNotFoundException {
        OdontologoDto odontologoDto = odontologoService.buscarPorId(1L);
        odontologoDto.setApellido("Diaz");
        odontologoDto.setNombre("Mora");
        odontologoDto.setMatricula("9854");
        odontologoService.actualizar(odontologoDto);
        Assert.assertTrue(odontologoService.buscarPorId(1L).getNombre().equals("Mora"));
        Assert.assertTrue(odontologoService.buscarPorId(1L).getApellido().equals("Diaz"));
        Assert.assertTrue(odontologoService.buscarPorId(1L).getMatricula().equals("9854"));
   }


}