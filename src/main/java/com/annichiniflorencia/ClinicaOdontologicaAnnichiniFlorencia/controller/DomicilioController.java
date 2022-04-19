package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.controller;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.DomicilioDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;

    @GetMapping("/listarTodos")
    public List<DomicilioDto> listarDomicilios(){
            return domicilioService.listarDomicilios();
        }

    @PostMapping("/guardar")
    public ResponseEntity<DomicilioDto> registrarDomicilio(@RequestBody DomicilioDto domicilioDto) throws BadRequestException {
        DomicilioDto domicilioPublicado = domicilioService.guardar(domicilioDto);
        return ResponseEntity.ok(domicilioPublicado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomicilioDto> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        DomicilioDto domicilioBuscado = domicilioService.buscarPorId(id);
        return ResponseEntity.ok(domicilioBuscado);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<DomicilioDto> actualizar(@RequestBody DomicilioDto domicilioDto) throws ResourceNotFoundException {
        DomicilioDto domicilioActualizado = domicilioService.actualizar(domicilioDto);
        return ResponseEntity.ok(domicilioActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        if (domicilioService.buscarPorId(id) != null) {
            domicilioService.eliminar(id);
        }
    }
}
