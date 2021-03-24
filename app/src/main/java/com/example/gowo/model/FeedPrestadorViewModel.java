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

public class FeedPrestadorViewModel extends ViewModel {

    String id;
    String categoria;

    MutableLiveData<List> prestServ;
    MutableLiveData<List<Servico>> servicos;
    MutableLiveData<Usuario> usuario;

    public FeedPrestadorViewModel(String id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public (LiveData<List<Servico>>, LiveData<Usuario>) getPrestServ() throws IOException {
        if (this.servicos == null){
            usuario = new MutableLiveData<>();
            servicos = new MutableLiveData<>();
            loadServicos();
        }
        return usuario, servicos;
    }

    void loadServicos() throws IOException {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
                public void run() {
                    List<Usuario> usuario = new ArrayList<>();
                    HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_list_category_services.php", "GET", "UTF-8");
                    httpRequest.addParam("category", categoria);
                    try {
                        InputStream is = httpRequest.execute();
                        String result = Util.inputStream2String(is, "UTF-8");
                        httpRequest.finish();

                        JSONObject jsonObject = new JSONObject(result);
                        int success = jsonObject.getInt("success");
                        if (success == 1) {
                            JSONArray jsonArray = jsonObject.getJSONArray("services");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jPrestador = jsonArray.getJSONObject(i);

                                String idPrest = jPrestador.getString("idUsr");
                                if (id.equals(idPrest)) {
                                    String nomeUsu = jPrestador.getString("userDoName");
                                    String imgBase64 = jPrestador.getString("userDoProfilePhoto");
                                    String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
                                    Bitmap imgUsu = Util.base642Bitmap(pureBase64Encoded);
                                    String endBairro = jPrestador.getString("sNbh");
                                    String endCidade = jPrestador.getString("sCity");

                                    Usuario u = new Usuario(idPrest, nomeUsu, imgUsu, endBairro, endCidade, categoria, imgBase64);
                                    usuario.postValue(u);

                                }
                            }
                        }

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.execute(new Runnable() {
                @Override
                public void run(){
                    List<Servico> servicosList = new ArrayList<>();

                    HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_list_worker_details.php", "GET", "UTF-8");
                    httpRequest.addParam("worker", id);
                    httpRequest.addParam("category", categoria);
                    try {
                        InputStream is = httpRequest.execute();
                        String result = Util.inputStream2String(is, "UTF-8");
                        httpRequest.finish();

                        JSONObject jsonObject = new JSONObject(result);
                        int success = jsonObject.getInt("success");
                        if (success == 1) {
                            JSONArray jsonArray = jsonObject.getJSONArray("servicesWorker");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jServico = jsonArray.getJSONObject(i);

                                String idServ = jServico.getString("serviceId");
                                String sName = jServico.getString("serviceName");
                                String imgBase64 = jServico.getString("servicePhoto");
                                String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
                                Bitmap imgServ = Util.base642Bitmap(pureBase64Encoded);
                                String sVal = jServico.getString("serviceVal");

                                Servico servico = new Servico(idServ, sName, sVal, imgServ, imgBase64);
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

    static public class FeedPrestadorViewModelFactory implements ViewModelProvider.Factory{

        String id;
        String categoria;

        public FeedPrestadorViewModelFactory(String id, String categoria) {
            this.id= id;
            this.categoria = categoria;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new FeedPrestadorViewModel(id, categoria);
        }
    }
}
