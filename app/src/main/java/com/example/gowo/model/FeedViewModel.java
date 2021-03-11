package com.example.gowo.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

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

    }
}
