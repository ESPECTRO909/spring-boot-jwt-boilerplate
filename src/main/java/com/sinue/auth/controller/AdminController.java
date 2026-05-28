package com.sinue.auth.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sinue.auth.dto.response.UsuarioResponseDTO;
import com.sinue.auth.model.enums.RoleUsuario;
import com.sinue.auth.service.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    // Inyectar el servicio de administración de usuarios
    private final AdminService adminService;

    // Constructor para inyectar el servicio
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Ver todos los usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> todosLosUsuarios() {
        return ResponseEntity.ok(adminService.todosLosUsuarios());
    }

    // Buscar usuario por id
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioResponseDTO> porId(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.porId(id));
    }

    // Cambiar rol de un usuario
    @PutMapping("/usuarios/{id}/rol")
    public ResponseEntity<Void> cambiarRol(
            @PathVariable Long id,
            @RequestParam RoleUsuario rol) {
        adminService.cambiarRol(id, rol);
        return ResponseEntity.ok().build();
    }

    // Eliminar usuario
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        adminService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    

}
