package com.sinue.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.sinue.auth.dto.PasswordChangeDTO;
import com.sinue.auth.dto.UsuarioResponseDTO;
import com.sinue.auth.dto.UsuarioUpdateDTO;
import com.sinue.auth.service.UsuarioService;

import java.util.Map;

@RestController
@PreAuthorize("hasAnyRole('USER'),'ADMIN')") // Solo usuarios autenticados pueden acceder a estos endpoints
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET /usuarios/me , Ver perfil propio(los datos propios)
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> miPerfil(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(usuarioService.obtenerPerfil(userDetails.getUsername()));
    }

    // PUT /usuarios/me , Actualizar nombre y/o correo(provicional despues reviso si es correcto cambiar el nombre)
    @PutMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> actualizarPerfil(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UsuarioUpdateDTO dto) {
        return ResponseEntity.ok(usuarioService.actualizarPerfil(userDetails.getUsername(), dto));
    }

    // PUT /usuarios/me/password , Cambiar contraseña (requiere la actual)
    @PutMapping("/me/password")
    public ResponseEntity<?> cambiarPassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody PasswordChangeDTO dto) {
        usuarioService.cambiarPassword(userDetails.getUsername(), dto);
        return ResponseEntity.ok(Map.of("mensaje", "Contraseña actualizada correctamente"));
    }
}
