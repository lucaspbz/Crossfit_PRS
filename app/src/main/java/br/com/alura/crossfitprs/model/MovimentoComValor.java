package br.com.alura.crossfitprs.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MovimentoComValor {
    @Embedded
    public Movimento movimento;
    @Relation(
            parentColumn = "idMovimento",
            entityColumn = "idMovimentoPai"
    )
    public List<ValorPr> valoresDePRMovimento;

    public ValorPr ultimaPr() {
        return valoresDePRMovimento.get(valoresDePRMovimento.size() - 1);
    }
}
