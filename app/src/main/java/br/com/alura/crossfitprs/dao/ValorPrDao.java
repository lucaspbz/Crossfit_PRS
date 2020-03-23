package br.com.alura.crossfitprs.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import br.com.alura.crossfitprs.model.ValorPr;

@Dao
public interface ValorPrDao {

    @Insert
    void adiciona(ValorPr valor);
}
