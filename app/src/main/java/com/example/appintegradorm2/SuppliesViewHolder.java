package com.example.appintegradorm2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appintegradorm2.model.Supplie;

import java.text.DateFormat;

public class SuppliesViewHolder extends RecyclerView.ViewHolder {

    private TextView tvDescricao;
    private TextView tvData;
    private ConstraintLayout clPai;
    private String idDoCompromisso;


    public SuppliesViewHolder(@NonNull View itemView) {
        super(itemView);

        //adicionando evento de clique no elemento principal da gaveta
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cast do contexto para activity atual e chamada do método
                ((MainActivity) v.getContext()).modificarCompromisso(v, idDoCompromisso);
            }
        });


        tvDescricao = itemView.findViewById(R.id.tvDescricao);
        tvData = itemView.findViewById(R.id.tvData);
        clPai = (ConstraintLayout) itemView;

    }

    public void atualizaGavetaComOObjetoQueChegou(Supplie c){
        //armazenando a posição do objeto na lista, para usar caso o método modificarCompromisso seja chamado
        idDoCompromisso = c.getId();

        tvDescricao.setText( c.getDescricao() );

        DateFormat formatador = android.text.format.DateFormat.getDateFormat( tvDescricao.getContext() );
        String dataFormatada = formatador.format( c.getData().getTime() );
        tvData.setText( dataFormatada );

        switch (c.getPrioridade()){
            case 0:
                clPai.setBackgroundColor( clPai.getResources().getColor(R.color.corCompromissoImportante, null) );
                break;
            case 1:
                clPai.setBackgroundColor( clPai.getResources().getColor(R.color.corCompromissoMedio, null) );
                break;
            default:
                clPai.setBackgroundColor( clPai.getResources().getColor(R.color.corCompromissoNaoImportante, null) );
        }

    }

}
