package br.com.alura.crossfitprs.ui.asyncTask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.crossfitprs.dao.MovimentosDao;
import br.com.alura.crossfitprs.model.Movimento;
import br.com.alura.crossfitprs.ui.adapter.ListaMovimentosAdapter;

public class ListaMovimentosTask extends AsyncTask {
    private MovimentosDao dao;
    private ListaMovimentosAdapter adapter;

    public ListaMovimentosTask(MovimentosDao dao, ListaMovimentosAdapter adapter) {

        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        List<Movimento> todosMovimentos = dao.todos();

        return todosMovimentos;
    }

    @Override
    protected void onPostExecute(Object todosMovimentos) {
        super.onPostExecute(todosMovimentos);
        adapter.atualiza((List<Movimento>) todosMovimentos);
    }
}
