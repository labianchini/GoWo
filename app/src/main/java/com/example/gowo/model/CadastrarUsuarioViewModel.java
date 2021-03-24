package com.example.gowo.model;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.ViewModel;

public class CadastrarUsuarioViewModel extends ViewModel {
    Uri selectPhotoLocation = null;

    public String getSelectPhotoLocation() {
        Log.d("select", String.valueOf(selectPhotoLocation));
        return String.valueOf(selectPhotoLocation);
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
