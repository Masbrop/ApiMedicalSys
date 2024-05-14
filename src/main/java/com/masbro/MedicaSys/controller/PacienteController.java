package com.masbro.MedicaSys.controller;

import com.masbro.MedicaSys.ProductoExcepciones.ResourceNoFoundExcepcion;
import com.masbro.MedicaSys.model.Paciente;
import com.masbro.MedicaSys.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/MedicaSys/")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:4200/")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    //Listar pacientes
    @GetMapping("/obtenerPacientes/{id_doctor}")
    public List<Paciente> listarTodosLosPacientes(@PathVariable Long id_doctor){
        return pacienteRepository.findByIdDoctor(id_doctor);
    }

    @GetMapping("/obtenerPacientes")
    public List<Paciente> listar(){
        return pacienteRepository.findAll();
    }

    //Guardar paciente
    @PostMapping("/pacientes")
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    //Buscar paciente por numero de documento
    @GetMapping("/pacientes/{documento}")
    public ResponseEntity<Paciente> obtenerPacientePorId(@PathVariable Long documento){
        Paciente paciente = pacienteRepository.findById(documento)
                .orElseThrow(() -> new ResourceNoFoundExcepcion(("No existe el paciente con el id" + documento)));
        return ResponseEntity.ok(paciente);
    }

    //Actualizar paciente
    @PutMapping("/pacientes/{documento}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Long documento, @RequestBody Paciente detallesPaciente){
        Paciente paciente = pacienteRepository.findById(documento)
                .orElseThrow(() -> new ResourceNoFoundExcepcion(("No existe el paciente con el id" + documento)));
        paciente.setNombre(detallesPaciente.getNombre());
        paciente.setEdad(detallesPaciente.getEdad());
        paciente.setTelefono(detallesPaciente.getTelefono());
        paciente.setDireccion(detallesPaciente.getDireccion());
        paciente.setIddoctor(detallesPaciente.getIddoctor());

        Paciente pacienteActualizado = pacienteRepository.save(paciente);
        return ResponseEntity.ok(pacienteActualizado);
    }
    
    //Actualizar historial
    @PutMapping("/historial/{documento}")
    public ResponseEntity<Paciente> actualizarHistorial(@PathVariable Long documento, @RequestBody Paciente detallesPaciente){
        Paciente paciente = pacienteRepository.findById(documento)
                .orElseThrow(() -> new ResourceNoFoundExcepcion(("No existe el paciente con el id" + documento)));
        paciente.setHistorial(detallesPaciente.getHistorial());
        Paciente pacienteActualizado = pacienteRepository.save(paciente);
        return ResponseEntity.ok(pacienteActualizado);
    }

    //Eliminar paciente
    @DeleteMapping("/pacientes/{documento}")
    public void eliminarPaciente(@PathVariable Long documento){
        Paciente paciente = pacienteRepository.findById(documento)
                .orElseThrow(() -> new ResourceNoFoundExcepcion(("No existe el paciente con el id" + documento)));
        pacienteRepository.delete(paciente);
    }

}
