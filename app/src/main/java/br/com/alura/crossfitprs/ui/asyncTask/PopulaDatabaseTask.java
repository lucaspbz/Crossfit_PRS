package br.com.alura.crossfitprs.ui.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import br.com.alura.crossfitprs.dao.MovimentosDao;
import br.com.alura.crossfitprs.model.Movimento;
import br.com.alura.crossfitprs.ui.adapter.ListaMovimentosAdapter;

public class PopulaDatabaseTask extends AsyncTask {

    private MovimentosDao movimentosDao;
    private Context context;

    public PopulaDatabaseTask(MovimentosDao movimentosDao, Context context) {
        this.movimentosDao = movimentosDao;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        movimentosDao.adiciona(Movimento.populaBancoDeDados());
        return movimentosDao.todos();
    }

    @Override
    protected void onPostExecute(Object todosMovimentos) {
        super.onPostExecute(todosMovimentos);
        ListaMovimentosAdapter adapter = new ListaMovimentosAdapter(context);
        //adapter.atualiza((List<Movimento>) todosMovimentos);
        adapter.notifyDataSetChanged();

    }
}
