package com.example.parcial_2_am_acn4bv_queimalinos.models;

import java.util.List;

public class Sesion {
    private String titulo;
    private List<Ejercicio> ejercicios;

    public Sesion(String titulo, List<Ejercicio> ejercicios) {
        this.titulo = titulo;
        this.ejercicios = ejercicios;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }
}
