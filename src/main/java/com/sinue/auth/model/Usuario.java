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

    private String folio;
    private String nombre;

    @Column(name = "username", unique = true, nullable = false)
    private String username;
    private int edad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "matricula", unique = true, nullable = false)
    private String matricula;
    
    private String correo;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RoleUsuario rol;// Enum para definir el rol del usuario (ADMIN, DENUNCIANTE, FUNCIONARIO)

    // Fecha en que se registró el usuario — se asigna automáticamente al crear
    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;

    private double puntosAcumulados; // Para el sistema de puntos

    public Usuario(){}

    public Usuario(long id, String folio,String nombre, String username, int edad, LocalDate fechaNacimiento, String matricula, String correo, String password, RoleUsuario rol, double puntosAcumulados) {
        this.id = id;
        this.folio = folio;
        this.nombre = nombre;
        this.username = username;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.matricula = matricula;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
        this.puntosAcumulados = puntosAcumulados;
    }

    // Getters y Setters
    public long getId(){
        return id;
    }

    public String getFolio()
    {
        return folio;
    }

    public String getNombre(){
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public int getEdad() {
        return edad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public String getMatricula(){
        return matricula;
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

    public double getPuntosAcumulados() {
        return puntosAcumulados;
    }

    // Setters

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setFolio(String folio){
        this.folio = folio;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
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

    public void setPuntosAcumulados(double puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }
}
