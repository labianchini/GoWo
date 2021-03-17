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
    String enderecoUsu;
    Bitmap imgUsu;

    public Usuario(String idUsu, String nameUsu, String sobrenomeUsu, String enderecoUsu, Bitmap imgUsu) {
        this.idUsu = idUsu;
        this.nameUsu = nameUsu;
        this.sobrenomeUsu = sobrenomeUsu;
        this.enderecoUsu = enderecoUsu;
        this.imgUsu = imgUsu;
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

    public String getEnderecoUsu() {
        return enderecoUsu;
    }

    public void setEnderecoUsu(String enderecoUsu) {
        this.enderecoUsu = enderecoUsu;
    }

    public Bitmap getImgUsu() {
        return imgUsu;
    }

    public void setImgUsu(Bitmap imgUsu) {
        this.imgUsu = imgUsu;
    }
}
