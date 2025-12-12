package com.example.parcial_2_am_acn4bv_queimalinos.models;

public class Ejercicio {

    private String idDocumento;
    private String createdAt;
    private String descripcion;
    private String elemento;
    private int id;
    private String nombre;
    private String parteCuerpo;
    private String imageUrl;

    public Ejercicio() {
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getElemento() {
        return elemento;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getParteCuerpo() {
        return parteCuerpo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
