package br.com.alura.crossfitprs.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movimento {

    @NonNull
    private final String nome;

    @PrimaryKey(autoGenerate = true)
    private int idMovimento;


    public Movimento(String nome) {
        this.nome = nome;
    }


    public static Movimento[] populaBancoDeDados() {
        return new Movimento[]{
                new Movimento("Clean N'Jerk"),
                new Movimento("Snatch"),
                new Movimento("Deadlift"),
                new Movimento("Split Jerk"),
                new Movimento("Push Jerk"),
                new Movimento("Press")
        };
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public String getNome() {
        return nome;
    }
}
