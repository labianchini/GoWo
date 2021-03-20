package com.example.gowo.model;

import android.graphics.Bitmap;

public class Usuario {
    String idUsu;
    String nameUsu;
    String sobrenomeUsu;
    String emailUsu;
    String senhaUsu;
    String telefoneUsu;
    String dataNascUsu;
    String endBairro;
    String endCidade;
    Bitmap imgUsu;
    String categoria;
    String photoStr;

    public Usuario(String idUsu, String nameUsu, Bitmap imgUsu, String endBairro, String endCidade, String categoria, String photoStr) {
        this.idUsu = idUsu;
        this.nameUsu = nameUsu;
        this.imgUsu = imgUsu;
        this.endBairro = endBairro;
        this.endCidade = endCidade;
        this.categoria = categoria;
        this.photoStr = photoStr;
    }

    public String getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(String idUsu) {
        this.idUsu = idUsu;
    }

    public String getNameUsu() {
        return nameUsu;
    }

    public void setNameUsu(String nameUsu) {
        this.nameUsu = nameUsu;
    }

    public String getSobrenomeUsu() {
        return sobrenomeUsu;
    }

    public void setSobrenomeUsu(String sobrenomeUsu) {
        this.sobrenomeUsu = sobrenomeUsu;
    }

    public String getEmailUsu() {
        return emailUsu;
    }

    public void setEmailUsu(String emailUsu) {
        this.emailUsu = emailUsu;
    }

    public String getSenhaUsu() {
        return senhaUsu;
    }

    public void setSenhaUsu(String senhaUsu) {
        this.senhaUsu = senhaUsu;
    }

    public String getTelefoneUsu() {
        return telefoneUsu;
    }

    public void setTelefoneUsu(String telefoneUsu) {
        this.telefoneUsu = telefoneUsu;
    }

    public String getDataNascUsu() {
        return dataNascUsu;
    }

    public void setDataNascUsu(String dataNascUsu) {
        this.dataNascUsu = dataNascUsu;
    }

    public String getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(String endBairro) {
        this.endBairro = endBairro;
    }

    public String getEndCidade() {
        return endCidade;
    }

    public void setEndCidade(String endCidade) {
        this.endCidade = endCidade;
    }

    public Bitmap getImgUsu() {
        return imgUsu;
    }

    public void setImgUsu(Bitmap imgUsu) {
        this.imgUsu = imgUsu;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPhotoStr() {
        return photoStr;
    }

    public void setPhotoStr(String photoStr) {
        this.photoStr = photoStr;
    }
}