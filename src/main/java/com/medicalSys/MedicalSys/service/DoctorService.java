package com.medicalSys.MedicalSys.service;

import com.medicalSys.MedicalSys.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public String hashPassword(String contraseña) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(contraseña.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public Boolean autenticarDoctor(Long usuario1, String contraseña1, Long usuario2, String contraseña2) throws NoSuchAlgorithmException {
        contraseña2 = hashPassword(contraseña2);
        return usuario1.equals(usuario2) && contraseña1.equals(contraseña2);
    }
}
