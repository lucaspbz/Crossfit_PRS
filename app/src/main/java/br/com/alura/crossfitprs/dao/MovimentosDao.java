package br.com.alura.crossfitprs.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import br.com.alura.crossfitprs.model.Movimento;
import br.com.alura.crossfitprs.model.MovimentoComValor;

@Dao
public interface MovimentosDao {

    @Insert
    void adiciona(Movimento... movimentos);

//    public void adiciona(List<Movimento> movimentos) {
//        todosMovimentos.addAll(movimentos);
//    }

    @Query("SELECT * FROM movimento")
    List<Movimento> todos();

//    public Movimento cria(String nomeMovimento, int pr) {
//        return new Movimento(nomeMovimento, pr);
//    }

    @Transaction
    @Query("SELECT * FROM movimento")
    public List<MovimentoComValor> getMovimentosComPr();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void edita(Movimento movimentoAEditar);
//    {
//        Movimento movimentoEncontrado = null;
//        for (Movimento m :
//                todosMovimentos) {
//            if (m.getId() == movimentoAEditar.getId()) {
//                movimentoEncontrado = m;
//                if (movimentoEncontrado != null) {
//                    int posicao = todosMovimentos.indexOf(movimentoEncontrado);
//                    todosMovimentos.set(posicao, movimentoEncontrado);
//                }
//            }
//        }
//    }

}
