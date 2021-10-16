package com.silveira.ceep.ui.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.silveira.ceep.R;
import com.silveira.ceep.adapters.NotasRecicleViewAdapter;
import com.silveira.ceep.model.Nota;
import com.silveira.ceep.model.dao.NotaDAO;

public class ListaNotas extends AppCompatActivity {

    RecyclerView listaNotas;
    ActivityResultLauncher<Intent> criarNota;
    NotasRecicleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        TextView addNota = getTextView();
        adapter = new NotasRecicleViewAdapter(NotaDAO.todos(), this);
        listaNotas.setAdapter(adapter);
        setActivityCriarNota();
        addNota.setOnClickListener((view)->{
            Intent intent = new Intent(this, FormCriacaoNota.class);
            criarNota.launch(intent);
        });


    }

    private TextView getTextView() {
        listaNotas = findViewById(R.id.recicleView);
        TextView addNota = findViewById(R.id.lista_notas_insere_nota);
        return addNota;
    }

    private void setActivityCriarNota() {
        criarNota = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent nota = result.getData();
                        assert nota != null;
                        Nota nota1 = (Nota) nota.getParcelableExtra("notaGerada");
                        System.out.println(nota1.toString());
                        adapter.addNota(nota1);
                    }
                }
        );
    }

}