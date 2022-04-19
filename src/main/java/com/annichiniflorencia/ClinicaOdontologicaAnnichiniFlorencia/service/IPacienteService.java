package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.PacienteDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface IPacienteService {

    List<PacienteDto> listarPacientes();
    PacienteDto buscarPorId(Long id) throws ResourceNotFoundException;
    PacienteDto guardar(PacienteDto pacienteDto) throws BadRequestException;
    PacienteDto actualizar (PacienteDto pacienteDto) throws ResourceNotFoundException;
    void eliminar(Long id) throws ResourceNotFoundException;

}
