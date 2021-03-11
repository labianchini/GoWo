package com.example.gowo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FeedViewModel {

    MutableLiveData<List<Servico>> servicos;

    public LiveData<List<Servico>> getServicos(){
        if (servicos == null){
            servicos = new MutableLiveData<>();
            loadServicos();
        }
        return servicos;
    }

    void loadServicos(){

    }
}
