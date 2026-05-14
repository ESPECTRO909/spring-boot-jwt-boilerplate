package com.sinue.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.sinue.auth.dto.AuthRequestDTO;
import com.sinue.auth.model.Usuario;
import com.sinue.auth.model.enums.RoleUsuario;
import com.sinue.auth.repository.UsuarioRepository;
import com.sinue.auth.security.JwtService;



@Service
public class AuthService {

    //inyección de dependencias
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;




    //REGISTRO
    public String register(AuthRequestDTO request) {

        if (usuarioRepository.existsByUsername(request.getUsername())) {// si el usuario ya existe, no se puede registrar
            return "El nombre de usuario ya existe";
        }

        else if (usuarioRepository.existsByMatricula(request.getMatricula())) {// si la matricula ya existe, no se puede registrar
            return "La matrícula ya existe";
        }


        Usuario user = new Usuario();
        user.setUsername(request.getUsername());
        user.setNombre(request.getNombre());
        user.setCorreo(request.getCorreo());
        user.setMatricula(request.getMatricula());  
        user.setFechaNacimiento(request.getFechaNacimiento());
        user.setCreadoEn(java.time.LocalDateTime.now()); // fecha de registro automática
        user.setPassword(passwordEncoder.encode(request.getPassword())); // hash
        user.setRol(RoleUsuario.USER); // se usa el enum para asignar el rol de usuario, por defecto es USER por seguridad, no se le asigna el rol de ADMIN a nadie

        try {
            usuarioRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            return "Error de datos, valida los campos del formulario";
        }

        return "Usuario registrado";
    }

    //LOGIN
    public String login(AuthRequestDTO request) {

    Usuario user = usuarioRepository.findFirstByUsername(request.getUsername())
            .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new BadCredentialsException("Credenciales inválidas");
    }

    //generar token
    return jwtService.generateToken(user.getUsername(), user.getRol().name());
}
}