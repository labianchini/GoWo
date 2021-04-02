package com.example.gowo.model;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gowo.R;
import com.example.gowo.activity.PerfilActivity;
import com.example.gowo.util.HttpRequest;
import com.example.gowo.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerfilViewModel {

    String id;
    String imagem;

    public PerfilViewModel(String id) {
        this.id = id;
    }

    public String getImagem(){
        if (this.imagem == null){
            imagem = new String();
            loadImagem();
        }
        return imagem;
    }

    public void loadImagem(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_take_user_photo_by_id.php", "GET", "UTF-8");
                httpRequest.addParam("id_user", id);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        String imgBase64 = jsonObject.getString("img");
                        imagem = imgBase64;
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static public class PerfilViewModelFactory implements ViewModelProvider.Factory{

        String id;

        public PerfilViewModelFactory(String id) {
            this.id = id;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PerfilViewModel(id);
        }
    }
}
