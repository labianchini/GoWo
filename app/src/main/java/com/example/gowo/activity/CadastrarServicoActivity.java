package com.example.gowo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.gowo.R;
import com.example.gowo.model.CadastrarServicoViewModel;
import com.example.gowo.util.Util;

public class CadastrarServicoActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;

    Uri selectPhotoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servico);

        Toolbar toolbar = findViewById(R.id.toolbarVoltar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageButton imgBtn = findViewById(R.id.imgBtnAddPhoto);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        CadastrarServicoViewModel cadastrarServicoViewModel = new ViewModelProvider(this).get(CadastrarServicoViewModel.class);
        String currenyPhotoPath = cadastrarServicoViewModel.getCurrentPhotoPath();
        if (!currenyPhotoPath.isEmpty()){
            ImageView imvPhotoPrev = findViewById(R.id.imvPhotoServ);
            Bitmap bitmap = Util.getBitmap(currenyPhotoPath, imvPhotoPrev.getWidth(), imvPhotoPrev.getHeight());
            imvPhotoPrev.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                selectPhotoLocation = data.getData();
                ImageView imvPhotoPrev = findViewById(R.id.imvPhotoServ);
                imvPhotoPrev.setImageURI(selectPhotoLocation);
            }
        }
    }
}