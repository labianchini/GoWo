package com.example.gowo.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gowo.util.HttpRequest;
import com.example.gowo.util.Util;

import java.io.IOException;
import java.io.InputStream;
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

    void loadServicos(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest("http://gowoifes.herokuapp.com/database/list_services.php", "GET", "UTF-8");
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    Log.d("HTTP_REQUEST_RESULT", result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
