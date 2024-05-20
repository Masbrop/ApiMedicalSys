package com.medicalSys.MedicalSys.controller;

import com.medicalSys.MedicalSys.ProductoExcepciones.ResourceNoFoundExcepcion;
import com.medicalSys.MedicalSys.model.Doctor;
import com.medicalSys.MedicalSys.model.Paciente;
import com.medicalSys.MedicalSys.repository.DoctorRepository;
import com.medicalSys.MedicalSys.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/MedicaSys/")
@CrossOrigin
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;
    private DoctorService doctorService = new DoctorService();

    //Listar doctores
    @GetMapping("/doctor")
    public List<Doctor> listarTodosLosDoctores(){
        return doctorRepository.findAll();
    }

    //Autenticar al doctor
    @PostMapping("/doctor/{documento}")
    public ResponseEntity<Doctor> obtenerDoctor(@PathVariable Long documento, @RequestBody Doctor doctor) throws NoSuchAlgorithmException {
        Doctor doctorBusqueda = doctorRepository.findById(documento)
                .orElseThrow(() -> new ResourceNoFoundExcepcion(("No existe el paciente con el id" + documento)));

        boolean validacion = doctorService.autenticarDoctor(
                doctorBusqueda.getIddoctor(), doctorBusqueda.getContrasena(), doctor.getIddoctor(), doctor.getContrasena());

        if(validacion){
            doctorBusqueda.setAutenticado(true);
            return ResponseEntity.ok(doctorBusqueda);
        }else{
            return null;
        }
    }

    //Guardar doctor
    @PostMapping("/doctor")
    public Doctor guardarDoctor(@RequestBody Doctor doctor) throws NoSuchAlgorithmException {
        doctor.setContrasena(doctorService.hashPassword(doctor.getContrasena()));
        return doctorRepository.save(doctor);
    }

    //Buscar doctor por numero de documento
    @GetMapping("/doctor/{documento}")
    public ResponseEntity<Doctor> obtenerDoctorPorId(@PathVariable Long documento){
        Doctor doctor = doctorRepository.findById(documento)
                .orElseThrow(() -> new ResourceNoFoundExcepcion(("Error en la busqueda")));
        doctor.setContrasena("*******************");
        return ResponseEntity.ok(doctor);
    }

}
