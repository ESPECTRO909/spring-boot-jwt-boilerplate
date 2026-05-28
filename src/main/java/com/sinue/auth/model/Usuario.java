package com.sinue.auth.model;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sinue.auth.model.enums.RoleUsuario;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Auto-incremental ID
    private long id;

    private String nombre;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String correo;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RoleUsuario rol;

    // Fecha en que se registró el usuario — se asigna automáticamente al crear
    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    public Usuario(){}

    public Usuario(long id, String nombre, String username, LocalDate fechaNacimiento, String correo, String password, RoleUsuario rol) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters
    public long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCorreo(){
        return correo;
    }

    public String getPassword(){
    return password;
    }

    public RoleUsuario getRol(){
        return rol;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    // Setters

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRol(RoleUsuario rol){
        this.rol = rol;
    }
}
