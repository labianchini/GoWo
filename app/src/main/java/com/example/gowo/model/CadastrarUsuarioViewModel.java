package com.example.gowo.model;

import androidx.lifecycle.ViewModel;

public class CadastrarUsuarioViewModel extends ViewModel {
    String currentPhotoPath = "";

    public void setCurrentPhotoPath(String currentPhotoPath){
        this.currentPhotoPath = currentPhotoPath;
    }

    public String getCurrentPhotoPath(){
        return currentPhotoPath;
    }
}
