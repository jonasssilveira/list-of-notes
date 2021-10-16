package com.silveira.ceep.model.dao;

import com.silveira.ceep.model.Nota;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotaDAO {

    private final static ArrayList<Nota> notas = new ArrayList<>();

    public static List<Nota> todos() {
        return (List<Nota>) notas.clone();
    }

    public static void insere(Nota... notas) {
        NotaDAO.notas.addAll(Arrays.asList(notas));
    }

    public static void altera(int posicao, Nota nota) {
        notas.set(posicao, nota);
    }

    public static void remove(int posicao) {
        notas.remove(posicao);
    }

    public static void troca(int posicaoInicio, int posicaoFim) {
        Collections.swap(notas, posicaoInicio, posicaoFim);
    }

    public static void removeTodos() {
        notas.clear();
    }
}