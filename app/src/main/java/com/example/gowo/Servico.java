package com.example.gowo;

import android.graphics.Bitmap;

public class Servico {
    String id;
    String name;
    String description;
    Bitmap photo;

    public Servico(String id, String name, String description, Bitmap photo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getPhoto() {
        return photo;
    }
}
