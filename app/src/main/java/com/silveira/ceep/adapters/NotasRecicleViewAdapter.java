package com.silveira.ceep.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.silveira.ceep.R;
import com.silveira.ceep.model.Nota;

import java.util.List;

public class NotasRecicleViewAdapter extends RecyclerView.Adapter<NotasRecicleViewAdapter.NotaViewHolder> {

    List<Nota> noteList;
    private Context context;

    public NotasRecicleViewAdapter(List<Nota> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotasRecicleViewAdapter.NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recicleView = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false);
        return new NotaViewHolder(recicleView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotasRecicleViewAdapter.NotaViewHolder holder, int position) {
        Nota nota = noteList.get(position);
        NotaViewHolder notaViewHolder = holder;
        notaViewHolder.vincula(nota);

    }
    public void addNota(Nota nota){
        noteList.add(nota);
        notifyItemInserted(noteList.size()-1);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    protected class NotaViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView descricao;
        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
        }
        void vincula(Nota nota){
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());
        }

    }

}
