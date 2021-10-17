package com.silveira.ceep.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.silveira.ceep.R;
import com.silveira.ceep.adapters.NotasRecicleViewAdapter;
import com.silveira.ceep.model.Nota;
import com.silveira.ceep.model.dao.NotaDAO;

public class ListaNotas extends AppCompatActivity {

    public static final String NOTA_EDITADA = "notaEditada";
    public static final String POSICAO = "posicao";
    public static final String NOTA_GERADA = "notaGerada";
    RecyclerView listaNotas;
    ActivityResultLauncher<Intent> criarNota;
    ActivityResultLauncher<Intent> editarNota;
    NotasRecicleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        TextView addNota = getTextView();
        adapter = new NotasRecicleViewAdapter(NotaDAO.todos(), this);
        setAdapter();
        editNota();
        setActivityCriarNota();
        addNota.setOnClickListener((view) -> {
            Intent intent = new Intent(this, FormCriacaoNota.class);
            criarNota.launch(intent);
        });

    }

    private void editNota() {
        editarNota = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent notaEditada = result.getData();
                    int posicao = notaEditada.getIntExtra(POSICAO, -1);
                    if (result.getResultCode() == Activity.RESULT_OK
                            && posicao > -1) {
                        assert notaEditada != null;
                        Nota nota = notaEditada.getParcelableExtra(NOTA_GERADA);
                        System.out.println("Posição: "+posicao+" Nota: "+ nota.toString());
                        adapter.setNota(posicao, nota);
                    }
                }
        );
    }

    private void setAdapter() {
        listaNotas.setAdapter(adapter);
        adapter.setOnClicKListener((nota, position) -> {
            Intent intent = new Intent(ListaNotas.this, FormCriacaoNota.class);
            System.out.println("Nota editada: " + nota);
            intent.putExtra(NOTA_EDITADA, nota);
            intent.putExtra(POSICAO, position);
            editarNota.launch(intent);
        });
    }

    private TextView getTextView() {
        listaNotas = findViewById(R.id.recicleView);
        return findViewById(R.id.lista_notas_insere_nota);
    }


    private void setActivityCriarNota() {
        criarNota = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Nota nota;
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intentNota = result.getData();
                        assert intentNota != null;
                        nota = intentNota.getParcelableExtra(NOTA_GERADA);
                        adapter.addNota(nota);
                    }
                }
        );
    }


}