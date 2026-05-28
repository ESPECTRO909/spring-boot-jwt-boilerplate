package com.sinue.auth.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sinue.auth.dto.requets.PasswordChangeDTO;
import com.sinue.auth.dto.requets.UsuarioUpdateDTO;
import com.sinue.auth.dto.response.UsuarioResponseDTO;
import com.sinue.auth.mapper.UsuarioMapper;
import com.sinue.auth.model.Usuario;
import com.sinue.auth.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponseDTO obtenerPerfil(String username) {
        Usuario usuario = usuarioRepository.findFirstByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toDTO(usuario);
    }

    public UsuarioResponseDTO actualizarPerfil(String username, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findFirstByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            usuario.setNombre(dto.getNombre());
        }
        if (dto.getCorreo() != null && !dto.getCorreo().isBlank()) {
            usuario.setCorreo(dto.getCorreo());
        }

        usuarioRepository.save(usuario);
        return toDTO(usuario);
    }
    // Cambiar contraseña (requiere la actual) con validaciones básicas
    public void cambiarPassword(String username, PasswordChangeDTO dto) {
        Usuario usuario = usuarioRepository.findFirstByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getPasswordActual(), usuario.getPassword())) {
            throw new BadCredentialsException("La contraseña actual es incorrecta");
        }

        if (dto.getPasswordNueva() == null || dto.getPasswordNueva().length() < 6) { // Validación básica de la nueva contraseña
            throw new IllegalArgumentException("La nueva contraseña debe tener al menos 6 caracteres");
        }

        usuario.setPassword(passwordEncoder.encode(dto.getPasswordNueva()));
        usuarioRepository.save(usuario);
    }

    // Método privado para convertir Usuario a UsuarioResponseDTO usando el mapper
    private UsuarioResponseDTO toDTO(Usuario usuario) {
        return usuarioMapper.toDTO(usuario);
    }
}
