package com.silveira.ceep.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.silveira.ceep.R;
import com.silveira.ceep.model.Nota;

public class FormCriacaoNota extends AppCompatActivity {

    public static final String TITLEBAR_CREATE_NOTA = "Insere nota";
    public static final String NOTA_GERADA = "notaGerada";
    public static final String NOTA_EDITADA = "notaEditada";
    public static final String POSICAO = "posicao";
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_criacao_nota);
        setTitle(TITLEBAR_CREATE_NOTA);
        Intent intent = getIntent();
        if (intent.hasExtra(NOTA_EDITADA)) {
            editedNota(intent);
        }
    }

    private void editedNota(Intent intent) {
        Nota nota = intent.getParcelableExtra(NOTA_EDITADA);
        posicao = intent.getIntExtra(POSICAO,-1);
        System.out.println("Nota cricação: "+ nota.toString());
        EditText titulo = findViewById(R.id.activity_form_criacao_nota_titulo);
        EditText descricao = findViewById(R.id.activity_form_criacao_nota_descricao);
        titulo.setText(nota.getTitulo());
        descricao.setText(nota.getDescricao());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.salva_nota_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.salvar_nota) {
            Nota nota = getNota();
            Intent intent = new Intent();
            intent.putExtra(NOTA_GERADA, nota);
            intent.putExtra(POSICAO, posicao);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private Nota getNota() {
        EditText titulo = findViewById(R.id.activity_form_criacao_nota_titulo);
        EditText descricao = findViewById(R.id.activity_form_criacao_nota_descricao);
        return new Nota(titulo.getText().toString(), descricao.getText().toString());
    }
}