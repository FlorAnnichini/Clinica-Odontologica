package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.DomicilioDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDomicilioService {

    List<DomicilioDto> listarDomicilios();
    DomicilioDto buscarPorId(Long id) throws ResourceNotFoundException;
    DomicilioDto guardar(DomicilioDto domicilioDto) throws BadRequestException;
    DomicilioDto actualizar (DomicilioDto domicilioDto) throws ResourceNotFoundException;
    void eliminar(Long id) throws ResourceNotFoundException;
}
