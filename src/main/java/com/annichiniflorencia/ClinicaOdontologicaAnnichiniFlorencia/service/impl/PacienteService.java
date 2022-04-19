package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.PacienteDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Paciente;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.repository.IPacienteRepository;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService{

    @Autowired
    IPacienteRepository pacienteRepository;
    @Autowired
    ObjectMapper mapper;

    Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired

    @Override
    public List<PacienteDto> listarPacientes() {
        List<PacienteDto> pacientesDto;
        List<Paciente> pacientes = pacienteRepository.findAll();
        pacientesDto = mapper.convertValue(pacientes, List.class);
        return pacientesDto;
    }

    @Override
    public PacienteDto buscarPorId(Long id) throws ResourceNotFoundException {
        Optional <Paciente> pacientePorId = pacienteRepository.findById(id);
        if (pacientePorId.isPresent()){
            return mapper.convertValue(pacientePorId.get(), PacienteDto.class);
        }else {
            throw new ResourceNotFoundException("No se encontro el paciente con el id indicado");
        }
    }

    @Override
    public PacienteDto guardar(PacienteDto pacienteDto) throws BadRequestException {
        if(pacienteDto.getNombre() == null || pacienteDto.getApellido() == null || pacienteDto.getDomicilio() == null || pacienteDto.getDni() == null){
            logger.error("Se produjo un error al guardar el paciente");
            throw new BadRequestException("No se pudo guardar el paciente por falta de datos");
        }
        Paciente pacienteAGuardar = mapper.convertValue(pacienteDto, Paciente.class);
        Paciente pacienteGuardado = pacienteRepository.save(pacienteAGuardar);
        return mapper.convertValue(pacienteGuardado, PacienteDto.class);
    }

    @Override
    public PacienteDto actualizar(PacienteDto pacienteDto) throws ResourceNotFoundException {
        if(buscarPorId(pacienteDto.getId()) == null){
            throw new ResourceNotFoundException("No se pudo actualizar el paciente correctamente");
        }
        logger.info("El paciente con id: "+ pacienteDto.getId() + " fue actualizado con exito");
        Paciente pacienteActualizar = mapper.convertValue(pacienteDto, Paciente.class);
        Paciente pacienteActualizado = pacienteRepository.saveAndFlush(pacienteActualizar);
        return mapper.convertValue(pacienteActualizado, PacienteDto.class);
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if (pacienteBuscado.isPresent()) {
            pacienteRepository.deleteById(id);
            logger.info("Se elimino con exito el paciente con id: " + id);
        } else {
            throw new ResourceNotFoundException("No se pudo eliminar con exito el paciente con id:" + id);
        }
    }
}
