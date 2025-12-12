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

    public String getCreatedAt() {
        return createdAt;
    }

    public List<EjercicioRef> getEjercicios() {
        return ejercicios;
    }

    public int getEntrenadorId() {
        return entrenadorId;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUpdatedAt() {
        return updatedAt;
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

        public int getSeries() {
            return series;
        }

        public int getReps() {
            return reps;
        }
    }
}
