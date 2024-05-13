package com.masbro.MedicaSys.controller;

import com.masbro.MedicaSys.ProductoExcepciones.ResourceNoFoundExcepcion;
import com.masbro.MedicaSys.model.Doctor;
import com.masbro.MedicaSys.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/MedicaSys/")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:4200/")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    //Listar doctores
    @GetMapping("/doctor")
    public List<Doctor> listarTodosLosDoctores(){
        return doctorRepository.findAll();
    }

    //Autenticar al usuario
    @PostMapping("/doctor/{documento}")
    public ResponseEntity<Doctor> obtenerDoctor(@PathVariable Long documento, @RequestBody Doctor doctor){
        Doctor doctorBusqueda = doctorRepository.findById(documento)
                .orElseThrow(() -> new ResourceNoFoundExcepcion(("No existe el paciente con el id" + documento)));
        if(doctorBusqueda.getContrasena().equals(doctor.getContrasena())){
            doctorBusqueda.setContrasena("***********");
            doctorBusqueda.setAutenticado(true);
            return ResponseEntity.ok(doctorBusqueda);
        }else{
            return null;
        }
    }

    //Guardar paciente
    @PostMapping("/doctor")
    public Doctor guardarDoctor(@RequestBody Doctor doctor){
        return doctorRepository.save(doctor);
    }
    /*
     @Autowired
    private PacienteRepository pacienteRepository;

    //Listar pacientes
    @GetMapping("/pacientes")
    public List<Paciente> listarTodosLosPacientes(){
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
        paciente.setIdDoctor(detallesPaciente.getIdDoctor());

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
     */
}
