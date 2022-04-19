package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.TurnoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface ITurnoService{

    List<TurnoDto> listarTurnos();
    TurnoDto buscarPorId(Long id) throws ResourceNotFoundException;
    TurnoDto guardar(TurnoDto turnoDto) throws BadRequestException;
    TurnoDto actualizar (TurnoDto turnoDto) throws ResourceNotFoundException;
    void eliminar(Long id) throws ResourceNotFoundException;
}
