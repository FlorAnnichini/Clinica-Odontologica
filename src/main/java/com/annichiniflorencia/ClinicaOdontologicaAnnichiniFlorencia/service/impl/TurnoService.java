package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.TurnoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Turno;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.repository.ITurnoRepository;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService implements ITurnoService{

    @Autowired
    ITurnoRepository turnoRepository;
    @Autowired
    ObjectMapper mapper;

    Logger logger = Logger.getLogger(TurnoService.class);

    @Autowired
    @Override
    public List<TurnoDto> listarTurnos() {
        List<TurnoDto> turnosDto;
        List<Turno> turnos = turnoRepository.findAll();
        turnosDto = mapper.convertValue(turnos, List.class);
        return turnosDto;
    }

    @Override
    public TurnoDto buscarPorId(Long id) throws ResourceNotFoundException {
        Optional <Turno> turnoPorId = turnoRepository.findById(id);
        if (turnoPorId.isPresent()){
            return mapper.convertValue(turnoPorId.get(), TurnoDto.class);
        }else {
            throw new ResourceNotFoundException("No se encontro el turno con el id indicado");
        }
    }

    @Override
    public TurnoDto guardar(TurnoDto turnoDto) throws BadRequestException {
        if(turnoDto.getOdontologo() == null || turnoDto.getPaciente() == null || turnoDto.getFecha() == null || turnoDto.getHora() == null){
            logger.error("Se produjo un error al guardar el turno");
            throw new BadRequestException("No se pudo guardar el turno por falta de datos");
        }
        Turno turnoAGuardar = mapper.convertValue(turnoDto, Turno.class);
        Turno turnoGuardado = turnoRepository.save(turnoAGuardar);
        return mapper.convertValue(turnoGuardado, TurnoDto.class);
    }

    @Override
    public TurnoDto actualizar(TurnoDto turnoDto) throws ResourceNotFoundException {
        if(buscarPorId(turnoDto.getId()) == null){
            throw new ResourceNotFoundException("No se pudo actualizar el turno correctamente");
        }
        logger.info("El turno con id: "+ turnoDto.getId() + " fue actualizado con exito");
        Turno turnoActualizar = mapper.convertValue(turnoDto, Turno.class);
        Turno turnoActualizado = turnoRepository.saveAndFlush(turnoActualizar);
        return mapper.convertValue(turnoActualizado, TurnoDto.class);

    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional <Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            turnoRepository.deleteById(id);
            logger.info("Se elimino con exito el turno con id: " + id);
        }else{
            throw new ResourceNotFoundException("No se pudo eliminar con exito el turno con id:" + id);
        }
    }

}
