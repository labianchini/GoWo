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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewServicoViewModel extends ViewModel {

    String id;

    MutableLiveData<Servico> servico;

    public ViewServicoViewModel(String id) { this.id = id; }

    public LiveData<Servico> getServico(){
        if (this.servico == null){
            servico = new MutableLiveData<Servico>();
            loadServico();
        }
        return servico;
    }

    void loadServico() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_service_details.php", "GET", "UTF-8");
                httpRequest.addParam("idService", id);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1){
                        JSONArray jsonArray = jsonObject.getJSONArray("service");
                        JSONObject jProduct = jsonArray.getJSONObject(0);

                        String idServ = jProduct.getString("idService");
                        String idPrest = jProduct.getString("idUserDo");
                        String nomeServ = jProduct.getString("sName");
                        String descricao = jProduct.getString("sDesc");
                        String valor = jProduct.getString("sVal");
                        String idEndereco = jProduct.getString("sAdressId");
                        String imgBase64 = jProduct.getString("sPhoto");
                        String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
                        Bitmap img = Util.base642Bitmap(pureBase64Encoded);
                        String categoria = jProduct.getString("sClass");

                        Servico s = new Servico();
                        s.setIdPrest(idPrest);
                        s.setIdServ(idServ);
                        s.setNameServ(nomeServ);
                        s.setDescriptionServ(descricao);
                        s.setValorServ(valor);
                        s.setIdEndereco(idEndereco);
                        s.setPhotoServ(img);
                        s.setCategoria(categoria);
                        servico.postValue(s);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static public class ViewServicoViewModelFactory implements ViewModelProvider.Factory{

        String id;

        public ViewServicoViewModelFactory(String id) {
            this.id = id;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ViewServicoViewModel(id);
        }
    }
}
