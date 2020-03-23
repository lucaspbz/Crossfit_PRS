package br.com.alura.crossfitprs.database;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

import br.com.alura.crossfitprs.dao.MovimentosDao;
import br.com.alura.crossfitprs.dao.ValorPrDao;
import br.com.alura.crossfitprs.model.Movimento;
import br.com.alura.crossfitprs.model.ValorPr;

@Database(entities = {Movimento.class, ValorPr.class}, version = 1)
public abstract class ListaMovimentosDatabase extends RoomDatabase {

    private static ListaMovimentosDatabase INSTANCE;

    public synchronized static ListaMovimentosDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
            Toast.makeText(context, "Populando...", Toast.LENGTH_SHORT).show();
        }

        return INSTANCE;
    }

    private static ListaMovimentosDatabase buildDatabase(final Context context) {
        return Room.
                databaseBuilder(context, ListaMovimentosDatabase.class, "Movimentos.db")
                .allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                MovimentosDao movimentosDao = getInstance(context).getMovimentosDao();
                                movimentosDao.adiciona(Movimento.populaBancoDeDados());
                            }
                        });
                    }
                })
                .build();
    }

    public abstract MovimentosDao getMovimentosDao();

    public abstract ValorPrDao getPrDao();


}
