package com.example.gowo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter {
    
    MainActivity mainActivity;

    public MyAdapter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
}
