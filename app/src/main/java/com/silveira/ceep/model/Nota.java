package com.silveira.ceep.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Nota implements Parcelable {

    private final String titulo;
    private final String descricao;

    public Nota(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    private Nota(Parcel parcel){
        this.titulo = parcel.readString();
        this.descricao = parcel.readString();
    }
    public static final Parcelable.Creator<Nota> CREATOR = new Parcelable.Creator<Nota>(){

        @Override
        public Nota createFromParcel(Parcel parcel) {
            return new Nota(parcel);
        }

        @Override
        public Nota[] newArray(int i) {
            return new Nota[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(descricao);
    }

    @NonNull
    @Override
    public String toString() {
        return "Titulo: "+titulo+", Descrição: "+descricao;
    }
}