package com.silveira.ceep.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.silveira.ceep.R;
import com.silveira.ceep.model.Nota;

public class FormCriacaoNota extends AppCompatActivity {

    public static final String TITLEBAR_CREATE_NOTA = "Insere nota";
    public static final String NOTA_GERADA = "notaGerada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_criacao_nota);
        setTitle(TITLEBAR_CREATE_NOTA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.salva_nota_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.salvar_nota) {
            Nota nota = getNota();
            Intent intent = new Intent();
            intent.putExtra(NOTA_GERADA, nota);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private Nota getNota() {
        EditText titulo = findViewById(R.id.activity_form_criacao_nota_titulo);
        EditText descricao = findViewById(R.id.activity_form_criacao_nota_descricao);
        Nota nota = new Nota(titulo.getText().toString(), descricao.getText().toString());
        return nota;
    }
}