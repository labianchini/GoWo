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

public class FeedPrestadorViewModel extends ViewModel {

    String id;
    String categoria;
    MutableLiveData<Usuario> usuario;
    MutableLiveData<List<Servico>> servicos;

    public FeedPrestadorViewModel(String id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public LiveData<Usuario> getUsuario(){
        if (this.usuario == null){
            usuario = new MutableLiveData<>();
            loadInfoPrestador();
        }
        return usuario;
    }

    public LiveData<List<Servico>> getServicos(){
        if (this.servicos == null){
            servicos= new MutableLiveData<>();
            loadServicos();
        }
        return servicos;
    }

    void loadInfoPrestador(){
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest("https://gowoifes.herokuapp.com/database/app/app_user_info.php", "GET", "UTF-8");
                httpRequest.addParam("id_user", id);
                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        String nomeUsu = jsonObject.getString("usrName");
                        String sobrenomeUsu = jsonObject.getString("usrLastName");
                        String telUsu = "+55" + jsonObject.getString("usrCellPhone");
                        String imgBase64 = jsonObject.getString("usrProfilePhoto");
                        String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
                        Bitmap imgUsu = Util.base642Bitmap(pureBase64Encoded);

                        Usuario u = new Usuario();
                        u.setImgUsu(imgUsu);
                        u.setNameUsu(nomeUsu);
                        u.setSobrenomeUsu(sobrenomeUsu);
                        u.setTelefoneUsu(telUsu);
                        usuario.postValue(u);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void loadServicos(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run () {
                List<Servico> meusServicosList = new ArrayList<>();

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

                            String id = jServico.getString("idUserDo");
                            String idServ = jServico.getString("serviceId");
                            String sName = jServico.getString("serviceName");
                            String imgBase64 = jServico.getString("servicePhoto");
                            String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
                            Bitmap imgServ = Util.base642Bitmap(pureBase64Encoded);
                            String sVal = jServico.getString("serviceVal");
                            String bairro = jServico.getString("sNbh");
                            String cidade = jServico.getString("sCity");
                            String estado = jServico.getString("sState");
                            String endereco = bairro + ", " + cidade + " - " + estado;


                            Servico s = new Servico();
                            s.setIdPrest(id);
                            s.setIdServ(idServ);
                            s.setNameServ(sName);
                            s.setPhotoServ(imgServ);
                            s.setValorServ(sVal);
                            s.setEndereco(endereco);
                            meusServicosList.add(s);

                        }
                        servicos.postValue(meusServicosList);
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
