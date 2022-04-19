package com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.repository;

import com.annichiniflorencia.ClinicaOdontologicaAnnichiniFlorencia.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository extends JpaRepository<Domicilio,Long> {
}
