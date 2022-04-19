package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.DomicilioDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DomicilioServiceTest {

    DomicilioService domicilioService;

    @Test
    public void buscarTodosLosDomiciliosTest() {
        List<DomicilioDto> domicilioDto = domicilioService.listarDomicilios();
        Assert.assertTrue(!domicilioDto.isEmpty());
        Assert.assertTrue(domicilioDto.size() > 0);
    }

    @Test
    public void guardarDomicilioTest() throws BadRequestException {
        DomicilioDto domicilioDto = domicilioService.guardar(new DomicilioDto("Las Magnolias", 818, "Lujan", "Santa fe"));
        Assert.assertTrue(domicilioDto.getId() != null);
    }

    @Test
    public void buscarDomicilioPorIdTest() throws ResourceNotFoundException {
        DomicilioDto domicilioDto = domicilioService.buscarPorId(1L);
        Assert.assertTrue(domicilioDto.getId() == null);
    }

    @Test
    public void actualizarDomicilioTest() throws ResourceNotFoundException {
        DomicilioDto domicilioDto = domicilioService.buscarPorId(1L);
        domicilioDto.setCalle("Las Magnolias");
        domicilioDto.setNumero(818);
        domicilioDto.setLocalidad("Lujan");
        domicilioDto.setProvincia("Santa fe");
        domicilioService.actualizar(domicilioDto);
        Assert.assertTrue(domicilioService.buscarPorId(1L).getCalle().equals("Las Magnolias"));
        Assert.assertTrue(domicilioService.buscarPorId(1L).getNumero().equals(818));
        Assert.assertTrue(domicilioService.buscarPorId(1L).getLocalidad().equals("Lujan"));
        Assert.assertTrue(domicilioService.buscarPorId(1L).getProvincia().equals("Santa fe"));
    }

    @Test
    public void eliminarDomicilioTest() throws ResourceNotFoundException {
        domicilioService.eliminar(1L);
        Assert.assertTrue(domicilioService.buscarPorId(1L) == null);
    }

}
