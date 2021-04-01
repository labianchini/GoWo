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

    /*public Servico(String idServ, String nameServ, String valorServ, Bitmap photoServ) {
        this.idServ = idServ;
        this.nameServ = nameServ;
        this.valorServ = valorServ;
        this.photoServ = photoServ;
        //this.endereco = endereco;
    }

    public Servico(String idServ, String idPrest, String nomeServ, String descricao, String valor, String idEndereco, Bitmap img, String categoria) {
        this.idServ = idServ;
        this.idPrest = idPrest;
        this.nameServ = nomeServ;
        this.descriptionServ = descricao;
        this.valorServ = valor;
        this.idEndereco = idEndereco;
        this.photoServ = img;
        this.categoria = categoria;
    }

    public Servico(String idUsu, String idServ, String sName, String sDesc, String sVal, Bitmap imgServ, String categoria, String endereco){
        this.idPrest = idUsu;
        this.idServ = idServ;
        this.nameServ = sName;
        this.descriptionServ = sDesc;
        this.valorServ = sVal;
        this.photoServ = imgServ;
        this.categoria = categoria;
        this.endereco = endereco;
    }*/

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
