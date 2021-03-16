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

public class FeedViewModel extends ViewModel {

    MutableLiveData<List<Servico>> servicos;

    public LiveData<List<Servico>> getServicos(){
        if (servicos == null){
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
                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/list_services.php", "GET", "UTF-8");
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);

                    JSONObject jsonObject = new JSONObject(result);
                    String success = jsonObject.getString("success[0]");
                            //getInt("success.get(0)");
                    //Log.i("success", String.valueOf(success));
                    if (success == "1"){
                        JSONArray jsonArray = jsonObject.getJSONArray("services_users");
                        for(int i = 0; i< jsonArray.length(); i++){
                            JSONObject jServico = jsonArray.getJSONObject(i);

                            String id_service = jServico.getString("id_service");
                            String service_name = jServico.getString("service_name");
                            String description = "123";
                            //String service_profile_img = jServico.getString("");

                            Servico servico = new Servico(id_service, service_name,description);
                            servicosList.add(servico);
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
