package com.app.Service;


import com.app.DTO.AuthenticationRequest;
import com.app.DTO.AuthenticationResponse;
import com.app.DTO.RegisterRequest;
import com.app.Model.*;
import com.app.Repository.PersonneRepository;
import com.app.Repository.TechnicienRepository;
import com.app.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PersonneRepository userdao;
    private final UserRepository userRepository;
    private final TechnicienRepository technicienRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = new Utilisateur();
        user.setNom(request.getNom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userdao.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())  // Include the role in the response
                .build();
    }

    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var admin = new AdministrateurIT();
        admin.setNom(request.getNom());
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole(Role.ADMIN);
        userdao.save(admin);

        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(admin.getRole().name())  // Include the role in the response
                .build();
    }

    public AuthenticationResponse registerTechnicien(RegisterRequest request) {
        var technicien = new TechnicienIT();
        technicien.setNom(request.getNom());
        technicien.setEmail(request.getEmail());
        technicien.setPassword(passwordEncoder.encode(request.getPassword()));
        technicien.setRole(Role.TECHNICIEN);
        userdao.save(technicien);

        var jwtToken = jwtService.generateToken(technicien);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(technicien.getRole().name())  // Include the role in the response
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userdao.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())  // Include the role in the response
                .build();
    }


    public List<Utilisateur> getAllUser() {
        return  userRepository.findAll();
    }
    public List<TechnicienIT> getAllTechnicien() {
        return  technicienRepository.findAll();
    }

    public Long count(){
        return userdao.count();
    }
}
