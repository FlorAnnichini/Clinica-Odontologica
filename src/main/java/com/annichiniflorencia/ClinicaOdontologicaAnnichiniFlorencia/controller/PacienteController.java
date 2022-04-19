package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.controller;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.OdontologoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.PacienteDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/guardar")
    public ResponseEntity<PacienteDto> guardarPaciente(@RequestBody PacienteDto pacienteDto) throws BadRequestException {
        PacienteDto paciente = pacienteService.guardar(pacienteDto);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/actualizar")
    public PacienteDto actualizarPaciente(@RequestBody PacienteDto pacienteDto) throws ResourceNotFoundException {
        return pacienteService.actualizar(pacienteDto);
    }

    @GetMapping("/{id}")
    public PacienteDto buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        return pacienteService.buscarPorId(id);
    }

    @DeleteMapping("/delete/{id}")
    public String eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        String respuesta="Error el id ingresado no es correcto";
        if (pacienteService.buscarPorId(id)!=null){
            pacienteService.eliminar(id);
            respuesta="Se elimino al paciente con id: " +id + " correctamente";
        }
        return respuesta;
    }

    @GetMapping("/listarTodos")
    public List<PacienteDto> listarPacientes(){
        return pacienteService.listarPacientes();
    }

}
