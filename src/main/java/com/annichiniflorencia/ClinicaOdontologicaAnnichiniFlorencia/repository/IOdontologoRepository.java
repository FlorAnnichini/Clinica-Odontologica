package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.repository;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo,Long> {
}
