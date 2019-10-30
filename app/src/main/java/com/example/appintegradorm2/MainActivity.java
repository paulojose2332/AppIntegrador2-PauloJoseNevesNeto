package com.example.appintegradorm2;

import android.content.Intent;
import android.os.Bundle;

import com.example.appintegradorm2.model.SupplieDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SupplieAdapter adaptador;
    private RecyclerView rvSupplies
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSupplies = findViewById(R.id.rvSupplies);

        adaptador = new SupplieAdapter();
        rvSupplies.setLayoutManager( new LinearLayoutManager(this));
        rvSupplies.setAdapter(adaptador);
    }

    public void modificarCompromisso(View v, String idDoCompromisso){
        Intent intencao = new Intent( this, GasFormActivity.class );
        intencao.putExtra("idCompromisso", idDoCompromisso);
        startActivityForResult(intencao, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            //significa que estava voltando da tela do formulário
            if (resultCode == 200){
                //atualizou um item
                int posicao = data.getIntExtra("posicaoDoObjetoEditado", -1);
                adaptador.notifyItemChanged( posicao );
                rvSupplies.smoothScrollToPosition(posicao);
            }else if (resultCode == 201){
                //adicionou um item
                Toast.makeText(this, "Compromisso inserido com sucesso", Toast.LENGTH_LONG).show();
                int posicao = SupplieDao.obterInstancia().obterLista().size()-1;
                adaptador.notifyItemInserted( posicao );
                rvSupplies.smoothScrollToPosition(posicao);
            }else if (resultCode == 202){
                //excluir um item
                Toast.makeText(this, "Compromisso excluído com sucesso", Toast.LENGTH_LONG).show();
                int posicao = data.getIntExtra("posicaoDoObjetoExcluido", -1);
                adaptador.notifyItemRemoved(posicao);
            }
        }
    }

    public void adicionarCompromisso(View v){
        Intent intencao = new Intent( this, GasFormActivity.class );
        startActivityForResult(intencao, 1);
    }
}
