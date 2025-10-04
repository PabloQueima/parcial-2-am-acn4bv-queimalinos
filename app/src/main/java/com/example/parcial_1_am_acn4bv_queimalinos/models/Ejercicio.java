package com.example.parcial_1_am_acn4bv_queimalinos.models;

public class Ejercicio {
    private String nombre;
    private int series;
    private int repeticiones;
    private String descripcion;
    private int imagenResId;

    public Ejercicio(String nombre, int series, int repeticiones, String descripcion, int imagenResId) {
        this.nombre = nombre;
        this.series = series;
        this.repeticiones = repeticiones;
        this.descripcion = descripcion;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSeries() {
        return series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
