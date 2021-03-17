package com.example.gowo.model;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.gowo.util.HttpRequest;
import com.example.gowo.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FeedCategoriaViewModel extends ViewModel {

    String categoria;

    MutableLiveData<List<Usuario>> usuarios;

    public FeedCategoriaViewModel(String categoria){ this.categoria = categoria; }

    public LiveData<List<Usuario>> getUsuarios(){
        if (this.usuarios == null){
            usuarios = new MutableLiveData<>();
            loadUsuarios();
        }
        return usuarios;
    }

    public void refreshUsuarios(){
        loadUsuarios();
    }

    void loadUsuarios(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Usuario> usuariosList = new ArrayList<>();

                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_list_category_services.php", "GET", "UTF-8");
                httpRequest.addParam("category", categoria);
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("services");
                        for(int i = 0; i< jsonArray.length(); i++){
                            JSONObject jPrestador = jsonArray.getJSONObject(i);

                            String nomeUsu = jPrestador.getString("userDoName");
                            String imgBase64 = jPrestador.getString("userDoProfilePhoto");
                            String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
                            Bitmap imgUsu = Util.base642Bitmap(pureBase64Encoded);
                            String endBairro = jPrestador.getString("sNbh");
                            String endCidade = jPrestador.getString("sCity");

                            Usuario usuario = new Usuario(nomeUsu, imgUsu, endBairro, endCidade);
                            usuariosList.add(usuario);
                        }
                        usuarios.postValue(usuariosList);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    static public class FeedCategoriaViewModelFactory implements ViewModelProvider.Factory{

        String categoria;

        public FeedCategoriaViewModelFactory(String categoria) {
            this.categoria = categoria;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new FeedCategoriaViewModel(categoria);
        }
    }
}
