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

public class MeusEnderecosViewModel extends ViewModel {

    String id;
    MutableLiveData<List<Endereco>> meuEndereco;

    public MeusEnderecosViewModel(String id) {
        this.id = id;
    }

    public LiveData<List<Endereco>> getMeusEnderecos(){
        if (this.meuEndereco == null){
            meuEndereco= new MutableLiveData<>();
            loadMeusEnderecos();
        }
        return meuEndereco;
    }

    public void loadMeusEnderecos(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run () {
                List<Endereco> meusEnderecosList = new ArrayList<>();

                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_list_address.php", "GET", "UTF-8");
                httpRequest.addParam("id_usr", id);
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getJSONArray("success").getInt(0);
                    if (success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("address_user");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jMeusEnderecos = jsonArray.getJSONObject(i);

                            String idAdress = jMeusEnderecos.getString("idAdress");
                            String adName = jMeusEnderecos.getString("adName");
                            String adCEP = jMeusEnderecos.getString("adCEP");
                            String adAddress = jMeusEnderecos.getString("adAddress");
                            String adNumber = jMeusEnderecos.getString("adNumber");
                            String adNbh = jMeusEnderecos.getString("adNbh");
                            String adCity = jMeusEnderecos.getString("adCity");
                            String adState = jMeusEnderecos.getString("adState");
                            String adComp = jMeusEnderecos.getString("adComp");

                            Endereco end = new Endereco();
                            end.setIdEnd(idAdress);
                            end.setApelido(adName);
                            end.setCep(adCEP);
                            end.setRua(adAddress);
                            end.setNumero(adNumber);
                            end.setBairro(adNbh);
                            end.setCidade(adCity);
                            end.setEstado(adState);
                            end.setComplemento(adComp);
                            meusEnderecosList.add(end);
                        }
                        meuEndereco.postValue(meusEnderecosList);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static public class MeusEnderecosViewModelFactory implements ViewModelProvider.Factory{

        String id;

        public MeusEnderecosViewModelFactory(String id) {
            this.id= id;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MeusEnderecosViewModel(id);
        }
    }
}
