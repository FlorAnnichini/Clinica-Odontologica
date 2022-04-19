package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.controller;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.OdontologoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.PacienteDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.dto.TurnoDto;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Odontologo;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Paciente;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.BadRequestException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.exceptions.ResourceNotFoundException;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl.OdontologoService;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl.PacienteService;
import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping("/guardar")
    public ResponseEntity<TurnoDto> guardarTurno(@RequestBody TurnoDto turnoDto) throws ResourceNotFoundException, BadRequestException {
        ResponseEntity<TurnoDto> respuesta;
        PacienteDto pacienteDto = pacienteService.buscarPorId(turnoDto.getPaciente().getId());
        OdontologoDto odontologoDto = odontologoService.buscarPorId(turnoDto.getOdontologo().getId());
        if (pacienteDto != null && odontologoDto != null) {
            respuesta = ResponseEntity.ok(turnoService.guardar(turnoDto));
        } else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<TurnoDto>> listarTurnos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PutMapping("/actualizar")
    public TurnoDto actualizarTurno(@RequestBody TurnoDto turnoDto) throws ResourceNotFoundException {
        return turnoService.actualizar(turnoDto);
    }

    @GetMapping("/{id}")
    public TurnoDto buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        return turnoService.buscarPorId(id);
    }

    @DeleteMapping("/delete/{id}")
    public String eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        String respuesta = "Error el id ingresado no es correcto";
        if (turnoService.buscarPorId(id) != null) {
            turnoService.eliminar(id);
            respuesta = "Se elimino el turno con id: " + id + " correctamente";
        }
        return respuesta;
    }
}
