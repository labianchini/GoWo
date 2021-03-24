package com.example.gowo.model;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.ViewModel;

public class CadastrarUsuarioViewModel extends ViewModel {
    String selectPhotoLocation = null;

    public String getSelectPhotoLocation() {
        return String.valueOf(selectPhotoLocation);
    }

    public void setSelectPhotoLocation(String selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
