package com.example.gowo.model;

import androidx.lifecycle.ViewModel;

public class CadastrarServicoViewModel extends ViewModel {
    //pegar a photo no cadastrar servico

    String currentPhotoPath = "";

    public String getCurrentPhotoPath() {
        return currentPhotoPath;
    }

    public void setCurrentPhotoPath(String currentPhotoPath) {
        this.currentPhotoPath = currentPhotoPath;
    }
}
