package com.sinue.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinue.auth.dto.response.UsuarioResponseDTO;
import com.sinue.auth.mapper.UsuarioMapper;
import com.sinue.auth.model.Usuario;
import com.sinue.auth.model.enums.RoleUsuario;
import com.sinue.auth.repository.UsuarioRepository;

import java.util.List;

@Service
@Transactional
public class AdminService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public AdminService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    // Ver todos los usuarios (sin exponer passwords usando dto)
    public List<UsuarioResponseDTO> todosLosUsuarios() {
        return usuarioRepository.findAll()
                .stream().map(this::toDTO).toList();
    }

    // Buscar usuario por id
    public UsuarioResponseDTO porId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toDTO(usuario);
    }

    // Cambiar rol de un usuario
    public void cambiarRol(Long id, RoleUsuario nuevoRol) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setRol(nuevoRol);
        usuarioRepository.save(usuario);
    }

    // Eliminar usuario
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    // Convertir entidad Usuario a DTO usando MapStruct
    private UsuarioResponseDTO toDTO(Usuario u) {
        return usuarioMapper.toDTO(u);
    }
}
