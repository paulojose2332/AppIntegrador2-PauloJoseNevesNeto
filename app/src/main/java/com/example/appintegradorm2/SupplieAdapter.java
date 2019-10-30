package com.example.appintegradorm2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appintegradorm2.model.Supplie;
import com.example.appintegradorm2.model.SupplieDao;

public class SupplieAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //1: inflar XML
        ConstraintLayout elementoPrincipalXML = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.supplie_item, parent, false
        );

        //2: associar os objetos inflados a um novo view holder
        SuppliesViewHolder gaveta = new SuppliesViewHolder(elementoPrincipalXML);

        //3: retornar o view holder que foi criado no passo 2

        Log.d("AULA", "Gaveta criada: "+gaveta);

        return gaveta;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Supplie c = SupplieDao.obterInstancia().obterLista().get(position);
        SuppliesViewHolder gaveta = (SuppliesViewHolder) holder;

        gaveta.atualizaGavetaComOObjetoQueChegou(c);
        Log.d("AULA", "Atualizou com o item na posição "+position+" a gaveta : "+gaveta);

    }

    @Override
    public int getItemCount() {
        return SupplieDao.obterInstancia().obterLista().size();
    }
}
