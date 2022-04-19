package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.OdontologoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IOdontologoService  {

    List<OdontologoDto> listarOdontologos();
    OdontologoDto buscarPorId(Long id) throws ResourceNotFoundException;
    OdontologoDto guardar(OdontologoDto odontologoDto) throws BadRequestException;
    OdontologoDto actualizar(OdontologoDto odontologoDto) throws ResourceNotFoundException;
    void eliminar(Long id) throws ResourceNotFoundException;

}
