package com.example.gowo.model;

import android.graphics.Bitmap;

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

public class MeusServicosViewModel extends ViewModel {

    String id;
    MutableLiveData<List<Servico>> meuServicos;

    public MeusServicosViewModel(String id) {
        this.id = id;
    }

    public LiveData<List<Servico>> getMeusServicos(){
        if (this.meuServicos == null){
            meuServicos= new MutableLiveData<>();
            loadMeusServicos();
        }
        return meuServicos;
    }

    public void loadMeusServicos(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run () {
                List<Servico> meusServicosList = new ArrayList<>();

                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_list_worker_details.php", "GET", "UTF-8");
                httpRequest.addParam("id_user", id);
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("services");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jMeusServico = jsonArray.getJSONObject(i);

                            String idUsu = jMeusServico.getString("idUserDo");
                            String idServ = jMeusServico.getString("idService");
                            String sName = jMeusServico.getString("sName");
                            String sDesc = jMeusServico.getString("sDesc");
                            String sVal = jMeusServico.getString("sVal");
                            String imgBase64 = jMeusServico.getString("sPhoto");
                            String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
                            Bitmap imgServ = Util.base642Bitmap(pureBase64Encoded);
                            String categoria = jMeusServico.getString("sClass");
                            String bairro = jMeusServico.getString("sNbh");
                            String cidade = jMeusServico.getString("sCity");
                            String estado = jMeusServico.getString("sState");
                            String endereco = bairro + ", " + cidade + " - " + estado;

                            Servico s = new Servico();
                            s.setIdPrest(idUsu);
                            s.setIdServ(idServ);
                            s.setNameServ(sName);
                            s.setDescriptionServ(sDesc);
                            s.setValorServ(sVal);
                            s.setPhotoServ(imgServ);
                            s.setCategoria(categoria);
                            s.setEndereco(endereco);
                            meusServicosList.add(s);
                        }
                        meuServicos.postValue(meusServicosList);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static public class MeusServicosViewModelFactory implements ViewModelProvider.Factory{

        String id;

        public MeusServicosViewModelFactory(String id) {
            this.id= id;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MeusServicosViewModel(id);
        }
    }
}