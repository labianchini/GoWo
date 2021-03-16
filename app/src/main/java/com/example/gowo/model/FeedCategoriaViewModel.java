package com.example.gowo.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    MutableLiveData<List<Servico>> servicos;

    public FeedCategoriaViewModel(String categoria){ this.categoria = categoria; }

    public LiveData<List<Servico>> getServicos(){
        if (this.servicos == null){
            servicos = new MutableLiveData<>();
            loadServicos();
        }
        return servicos;
    }

    public void refreshServicos(){
        loadServicos();
    }

    void loadServicos(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Servico> servicosList = new ArrayList<>();

                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_list_category_services.php", "GET", "UTF-8");
                httpRequest.addParam("sClass", categoria);
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
                            JSONObject jServico = jsonArray.getJSONObject(i);

                            String idUsu = jServico.getString("idUser");
                            String nomeUsu = jServico.getString("usrName");
                            String sobrenomeUsu = jServico.getString("usrLastName");
                            String imgBase64 = jServico.getString("usrProfilePhoto");
                            String imgUsu = imgBase64.substring(imgBase64.indexOf(",") + 1);
                            String endereço = jServico.getString("usrActiveAdress");

                            //Servico servico = new Servico(idUsu, nomeUsu, sobrenomeUsu, imgUsu, endereço);
                            //servicosList.add(servico);
                        }
                        servicos.postValue(servicosList);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
