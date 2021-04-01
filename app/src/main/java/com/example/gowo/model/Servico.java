package com.example.gowo.model;

import android.graphics.Bitmap;

public class Servico {
    String idServ;
    String idPrest;
    String nameServ;
    String descriptionServ;
    String valorServ;
    Bitmap photoServ;
    String endereco;
    String idEndereco;
    String categoria;

    public Servico() {
    }

    public String getIdServ() {
        return idServ;
    }

    public void setIdServ(String idServ) {
        this.idServ = idServ;
    }

    public String getIdPrest() {
        return idPrest;
    }

    public void setIdPrest(String idPrest) {
        this.idPrest = idPrest;
    }

    public String getNameServ() {
        return nameServ;
    }

    public void setNameServ(String nameServ) {
        this.nameServ = nameServ;
    }

    public String getDescriptionServ() {
        return descriptionServ;
    }

    public void setDescriptionServ(String descriptionServ) {
        this.descriptionServ = descriptionServ;
    }

    public String getValorServ() {
        return valorServ;
    }

    public void setValorServ(String valorServ) {
        this.valorServ = valorServ;
    }

    public Bitmap getPhotoServ() {
        return photoServ;
    }

    public void setPhotoServ(Bitmap photoServ) {
        this.photoServ = photoServ;
    }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getIdEndereco() { return idEndereco; }

    public void setIdEndereco(String idEndereco) { this.idEndereco = idEndereco; }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }
}
