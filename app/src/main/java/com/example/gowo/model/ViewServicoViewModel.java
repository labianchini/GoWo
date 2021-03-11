package com.example.gowo.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewServicoViewModel extends ViewModel {
    //aparecer as informacoes do servico

    String id;

    MutableLiveData<Servico> servico;

    public ViewServicoViewModel(String id) {
        this.id = id;
    }

    public LiveData<Servico> getServico(){  //pegar os detalhes do servico
        if (this.servico == null){
            servico = new MutableLiveData<Servico>();
            loadServico();
        }
        return servico;
    }

    void loadServico(){

    }

    static public class ViewServicoViewModelFactory implements ViewModelProvider.Factory{
        //para o construtor aceitar parâmetros

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
