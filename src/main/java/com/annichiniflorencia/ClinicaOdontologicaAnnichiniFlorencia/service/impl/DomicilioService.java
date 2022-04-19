package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl;


import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.DomicilioDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Domicilio;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.repository.IDomicilioRepository;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.IDomicilioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IDomicilioService {

    @Autowired
    IDomicilioRepository domicilioRepository;
    @Autowired
    ObjectMapper mapper;

    Logger logger = Logger.getLogger(DomicilioService.class);


    @Override
        public List<DomicilioDto> listarDomicilios(){
        List<DomicilioDto> respuesta;
        List<Domicilio> listaDomicilios = domicilioRepository.findAll();
        respuesta = mapper.convertValue(listaDomicilios, List.class);
        return respuesta;
    }

    @Override
    public DomicilioDto guardar(DomicilioDto domicilioDto) throws BadRequestException {
        if (domicilioDto.getCalle() == null || domicilioDto.getNumero() == null || domicilioDto.getLocalidad() == null || domicilioDto.getLocalidad() == null){
            logger.error("Se produjo un error al guardar el domicilio");
            throw new BadRequestException("No se puede guardar el domicilio, faltan datos");
        }
        Domicilio domicilioGuardado = mapper.convertValue(domicilioDto, Domicilio.class);
        Domicilio domicilio = domicilioRepository.save(domicilioGuardado);

        return mapper.convertValue(domicilio, DomicilioDto.class);
    }

    @Override
    public DomicilioDto buscarPorId(Long id) throws ResourceNotFoundException {
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        if (domicilio.isPresent()) {
            return mapper.convertValue(domicilio.get(), DomicilioDto.class);
        }
        else {
            throw new ResourceNotFoundException("No se encontro el domicilio con id: " + id);
        }
    }

    @Override
    public DomicilioDto actualizar(DomicilioDto domicilioDto) throws ResourceNotFoundException{
        if (buscarPorId(domicilioDto.getId()) == null) {
            logger.error("Se produjo un error al actualizar el domicilio");
            throw new ResourceNotFoundException("No se encontro el domicilio con id: " + domicilioDto.getId());
        }
        logger.info("Se actualizo el domicilio con id: " + domicilioDto.getId());
        Domicilio domicilioGuardado = mapper.convertValue(domicilioDto, Domicilio.class);
        Domicilio domicilio = domicilioRepository.save(domicilioGuardado);
        return mapper.convertValue(domicilio, DomicilioDto.class);
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        Optional<Domicilio> domicilio = domicilioRepository.findById(id);
        if (domicilio.isPresent()) {
            domicilioRepository.deleteById(id);
            logger.info("Se elimino el domicilio con id: " + id);
        } else throw new ResourceNotFoundException("No se encontro el domicilio con id: " + id);
    }

}
