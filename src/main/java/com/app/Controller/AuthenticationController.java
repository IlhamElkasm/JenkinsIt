package com.app.Controller;


import com.app.DTO.AuthenticationRequest;
import com.app.DTO.AuthenticationResponse;
import com.app.DTO.RegisterRequest;
import com.app.Model.Panne;
import com.app.Model.Personne;
import com.app.Model.TechnicienIT;
import com.app.Model.Utilisateur;
import com.app.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @GetMapping("/Admin/AllUser")
    public List<Utilisateur> getAllPanne() {
        return authService.getAllUser();
    }

    @GetMapping("/Admin/getAllTechnicien")
    public List<TechnicienIT> getAllTechnicien() {
        return authService.getAllTechnicien();
    }

    @PostMapping("/Admin/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.registerAdmin(request));
    }
    @PostMapping("/Admin/registerTechnicien")
    public ResponseEntity<AuthenticationResponse> registerTechnicien(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.registerTechnicien(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));

    }



}