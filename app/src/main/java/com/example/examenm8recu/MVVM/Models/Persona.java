package com.example.examenm8recu.MVVM.Models;

public class Persona {

    private String id;
    private String nombre;
    private String curso;
    private String edad;

    public Persona(String id, String nombre, String curso, String edad) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.edad = edad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
