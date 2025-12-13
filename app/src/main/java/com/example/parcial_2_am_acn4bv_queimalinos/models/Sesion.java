package com.example.parcial_2_am_acn4bv_queimalinos.models;

import java.util.List;

public class Sesion {

    private String idDocumento;
    private int clienteId;
    private String createdAt;
    private List<EjercicioRef> ejercicios;
    private int entrenadorId;
    private long id;
    private String titulo;
    private String updatedAt;

    public Sesion() {
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<EjercicioRef> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<EjercicioRef> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public int getEntrenadorId() {
        return entrenadorId;
    }

    public void setEntrenadorId(int entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class EjercicioRef {
        private int id;
        private int series;
        private int reps;

        public EjercicioRef() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSeries() {
            return series;
        }

        public void setSeries(int series) {
            this.series = series;
        }

        public int getReps() {
            return reps;
        }

        public void setReps(int reps) {
            this.reps = reps;
        }
    }
}
