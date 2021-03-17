package com.example.gowo.model;

import android.graphics.Bitmap;

public class Servico {
    String idServ;
    String idPrest;
    String nameServ;
    String descriptionServ;
    String valorServ;
    String enderecoServ;
    Bitmap photoServ;

    public Servico(String idServ, String nameServ, String descriptionServ) {
        this.idServ = idServ;
        this.nameServ = nameServ;
        this.descriptionServ = descriptionServ;
    }

    public Servico(String idServ, String idPrest, String nameServ, String descriptionServ, Bitmap photoServ) {
        this.idServ = idServ;
        this.idPrest = idPrest;
        this.nameServ = nameServ;
        this.descriptionServ = descriptionServ;
        this.photoServ = photoServ;
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

    public String getEnderecoServ() {
        return enderecoServ;
    }

    public void setEnderecoServ(String enderecoServ) {
        this.enderecoServ = enderecoServ;
    }

    public Bitmap getPhotoServ() {
        return photoServ;
    }

    public void setPhotoServ(Bitmap photoServ) {
        this.photoServ = photoServ;
    }
}
