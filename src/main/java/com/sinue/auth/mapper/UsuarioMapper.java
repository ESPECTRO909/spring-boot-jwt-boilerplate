package com.sinue.auth.mapper;

import org.springframework.stereotype.Component;

import com.sinue.auth.dto.response.UsuarioResponseDTO;
import com.sinue.auth.model.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        //dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setFechaNacimiento(usuario.getFechaNacimiento());
        dto.setRol(usuario.getRol());
        dto.setCreadoEn(usuario.getCreadoEn());
        return dto;
    }
}
