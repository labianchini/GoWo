package com.example.gowo.model;

import android.graphics.Bitmap;

public class Servico {
    String idServ;
    String idPrest;
    String nameServ;
    String descriptionServ;
    Bitmap photoServ;

    public Servico(String idServ, String idPrest, String nameServ, String descriptionServ, Bitmap photoServ) {
        this.idServ = idServ;
        this.idPrest = idPrest;
        this.nameServ = nameServ;
        this.descriptionServ = descriptionServ;
        this.photoServ = photoServ;
    }

    public String getIdServ() { return idServ;
    }

    public String getIdPrest() {
        return idPrest;
    }

    public String getNameServ() {
        return nameServ;
    }

    public String getDescriptionServ() {
        return descriptionServ;
    }

    public Bitmap getPhotoServ() {
        return photoServ;
    }
}
