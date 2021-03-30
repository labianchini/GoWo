package com.example.gowo.model;

import androidx.lifecycle.ViewModel;

public class CadastrarServicoViewModel extends ViewModel {
    String selectPhotoLocation = null;

    public String getSelectPhotoLocation() { return selectPhotoLocation; }

    public void setSelectPhotoLocation(String selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
