package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.repository;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Long> {
}
