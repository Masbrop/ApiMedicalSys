package com.medicalSys.MedicalSys.repository;

import com.medicalSys.MedicalSys.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE p.iddoctor = :iddoctor")
    List<Paciente> findByIdDoctor(@Param("iddoctor") Long iddoctor);
}
