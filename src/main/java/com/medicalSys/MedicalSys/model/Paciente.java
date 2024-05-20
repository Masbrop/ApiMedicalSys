package com.medicalSys.MedicalSys.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pacientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @Column(name = "documento", nullable = false)
    private Long documento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "telefono", nullable = false)
    private Long telefono;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "historial", length = 50000)
    private String historial;

    @Column(name = "iddoctor", nullable = false)
    private Long iddoctor;
}
