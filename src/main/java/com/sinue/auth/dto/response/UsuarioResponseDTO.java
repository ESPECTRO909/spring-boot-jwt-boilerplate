package com.sinue.auth.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sinue.auth.model.enums.RoleUsuario;

public class UsuarioResponseDTO {

   // private Long id;//temporal 
    private String username;
    private String nombre;
    private String correo;
    private LocalDate fechaNacimiento;
    private RoleUsuario rol;
    private LocalDateTime creadoEn;

   // public Long getId() { no deberia el usuario saber su id? no es necesario mostrarlo en la respuesta
     //   return id; }
   // public void setId(Long id) {
     //   this.id = id; }

    public String getUsername() { 
        return username; }

    public void setUsername(String username) {
        this.username = username; }

    public String getNombre() {
        return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre; }

    public String getCorreo() {
        return correo; }

    public void setCorreo(String correo) { 
        this.correo = correo; }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento; }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento; }

    public RoleUsuario getRol() {
        return rol; }

    public void setRol(RoleUsuario rol) {
        this.rol = rol; }

    public LocalDateTime getCreadoEn() {
        return creadoEn; }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn; }
}
