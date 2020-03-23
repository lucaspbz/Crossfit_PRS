package br.com.alura.crossfitprs.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ValorPr {
    @PrimaryKey(autoGenerate = true)
    private int idValorPr;
    private int idMovimentoPai;
    private double valorPr;

    public ValorPr(int idMovimentoPai, double valorNovaPr) {
        this.idMovimentoPai = idMovimentoPai;
        this.valorPr = valorNovaPr;
    }

    public ValorPr(){}
    //private Calendar data;

    public void setNovaPr(int id, double novaPr) {
        this.idMovimentoPai = id;
        this.valorPr = novaPr;
        //this.data = Calendar.getInstance();

    }

    public int getIdValorPr() {
        return idValorPr;
    }

    public void setIdValorPr(int idValorPr) {
        this.idValorPr = idValorPr;
    }

    public int getIdMovimentoPai() {
        return idMovimentoPai;
    }

    public void setIdMovimentoPai(int idMovimentoPai) {
        this.idMovimentoPai = idMovimentoPai;
    }

    public double getValorPr() {
        return valorPr;
    }

    public void setValorPr(double valorPr) {
        this.valorPr = valorPr;
    }
}
