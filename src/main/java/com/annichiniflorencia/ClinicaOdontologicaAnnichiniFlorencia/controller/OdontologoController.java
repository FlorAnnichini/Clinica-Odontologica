package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.controller;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.OdontologoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping("/listarTodos")
    public List<OdontologoDto> buscarTodosOdontologos() {
        return odontologoService.listarOdontologos();
    }

    @DeleteMapping("/delete/{id}")
    public String eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        String respuesta = "Error el id ingresado no es correcto";
        if (odontologoService.buscarPorId(id) != null) {
            odontologoService.eliminar(id);
            respuesta = "Se elimino al odontologo con id: " + id + " correctamente";
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public OdontologoDto buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        return odontologoService.buscarPorId(id);
    }


    @PostMapping("/guardar")
    public ResponseEntity<OdontologoDto> guardarOdontologo(@RequestBody OdontologoDto odontologoDto) throws BadRequestException {
        OdontologoDto odontologo = odontologoService.guardar(odontologoDto);
        return ResponseEntity.ok(odontologo);
    }

    @PutMapping("/actualizar")
    public OdontologoDto actualizarOdontologo(@RequestBody OdontologoDto odontologoDto) throws ResourceNotFoundException {
        return odontologoService.actualizar(odontologoDto);
    }

}