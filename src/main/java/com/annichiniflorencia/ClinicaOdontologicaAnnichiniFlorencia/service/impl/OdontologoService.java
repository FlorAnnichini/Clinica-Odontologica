package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.OdontologoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Odontologo;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.repository.IOdontologoRepository;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class OdontologoService implements IOdontologoService{

    @Autowired
    IOdontologoRepository odontologoRepository;
    @Autowired
    ObjectMapper mapper;

    private static final Logger logger = Logger.getLogger(OdontologoService.class);

    @Autowired
    @Override
    public List<OdontologoDto> listarOdontologos() {
        List<OdontologoDto> odontologosDto;
        List<Odontologo> odontologos = odontologoRepository.findAll();
        odontologosDto = mapper.convertValue(odontologos, List.class);
        return odontologosDto;
    }

    @Override
    public OdontologoDto buscarPorId(Long id) throws ResourceNotFoundException {
        Optional <Odontologo> odontologoPorId = odontologoRepository.findById(id);
        if (odontologoPorId.isPresent()){
           return mapper.convertValue(odontologoPorId.get(), OdontologoDto.class);
        }else {
            throw new ResourceNotFoundException("No se encontro el odontologo con el id indicado");
        }
    }

    @Override
    public OdontologoDto guardar(OdontologoDto odontologoDto) throws BadRequestException {
        if(odontologoDto.getNombre() == null || odontologoDto.getApellido() == null || odontologoDto.getMatricula() == null){
            logger.error("Se produjo un error al guardar el odontologo");
            throw new BadRequestException("No se pudo guardar el odontologo por falta de datos");
        }
        Odontologo odontologoAGuardar = mapper.convertValue(odontologoDto, Odontologo.class);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologoAGuardar);
        return mapper.convertValue(odontologoGuardado, OdontologoDto.class);
    }

    @Override
    public OdontologoDto actualizar(OdontologoDto odontologoDto) throws ResourceNotFoundException {
        if(buscarPorId(odontologoDto.getId()) == null){
            throw new ResourceNotFoundException("No se pudo actualizar el odontologo correctamente");
        }
        logger.info("El odontologo con id: "+ odontologoDto.getId() + " fue actualizado con exito");
        Odontologo odontologoActualizar = mapper.convertValue(odontologoDto, Odontologo.class);
        Odontologo odontologoActualizado = odontologoRepository.saveAndFlush(odontologoActualizar);
        return mapper.convertValue(odontologoActualizado, OdontologoDto.class);
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional <Odontologo> odontologoBuscado = odontologoRepository.findById(id);
        if (odontologoBuscado.isPresent()){
            odontologoRepository.deleteById(id);
            logger.info("Se elimino con exito el odontologo con id: " + id);
        }else{
            throw new ResourceNotFoundException("No se pudo eliminar con exito el odontolgo con id:" + id);
        }
    }
}
