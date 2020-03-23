package br.com.alura.crossfitprs.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.crossfitprs.R;
import br.com.alura.crossfitprs.dao.MovimentosDao;
import br.com.alura.crossfitprs.dao.ValorPrDao;
import br.com.alura.crossfitprs.database.ListaMovimentosDatabase;
import br.com.alura.crossfitprs.model.Movimento;
import br.com.alura.crossfitprs.model.MovimentoComValor;
import br.com.alura.crossfitprs.model.ValorPr;
import br.com.alura.crossfitprs.ui.adapter.ListaMovimentosAdapter;
import br.com.alura.crossfitprs.ui.adapter.OnItemClickListener;
import br.com.alura.crossfitprs.ui.asyncTask.ListaMovimentosTask;

public class ListaMovimentos extends AppCompatActivity {

    private ListaMovimentosAdapter adapter;
    private MovimentosDao movimentosDao;
    private ValorPrDao valorPrDao;
    private ListaMovimentosDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_movimentos);
        ListView listaMovimentosListView = findViewById(R.id.lista_movimentos_listview);

        database = ListaMovimentosDatabase.getInstance(this);
        movimentosDao = database.getMovimentosDao();
        valorPrDao = database.getPrDao();
        adapter = new ListaMovimentosAdapter(this);

        buscaListaDeMovimentos();

        configuraAdapter(listaMovimentosListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void configuraAdapter(ListView listaDeMovimentos) {
        listaDeMovimentos.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Movimento movimento) {
                abreDialogEditaPr(movimento);
            }
        });
    }

    private void abreDialogEditaPr(Movimento movimento) {

        View viewCriada = LayoutInflater.from(ListaMovimentos.this).inflate(R.layout.dialog_view_teste, null);
        preencheCamposDialog(movimento, viewCriada);
        configuraDialog(viewCriada, movimento);
    }

    private void configuraDialog(final View viewCriada, final Movimento movimentoEscolhido) {
        new AlertDialog.Builder(ListaMovimentos.this)
                .setTitle("Nova PR")
                .setMessage("Parabéns! Digite sua nova PR:")
                .setView(viewCriada)
                .setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ListaMovimentos.this, "Positivo clicado", Toast.LENGTH_SHORT).show();
                        editaValorPr(viewCriada, movimentoEscolhido);
                    }
                })
                .setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ListaMovimentos.this, "Negativo clicado", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void editaValorPr(View viewCriada, Movimento movimentoEscolhido) {
        EditText valorNovoPrView = viewCriada.findViewById(R.id.dialog_valor_pr);
        if (!valorNovoPrView.getText().toString().equals("")) {
            String novaPr = String.valueOf(valorNovoPrView.getText());
            double novaPrFormatada = Double.parseDouble(novaPr);
            ValorPr valorPrNova = new ValorPr(movimentoEscolhido.getIdMovimento(), novaPrFormatada);
            valorPrDao.adiciona(valorPrNova);
        }
    }

    private void preencheCamposDialog(Movimento movimento, View viewCriada) {
        TextView nomeMovimento = viewCriada.findViewById(R.id.dialog_nome_movimento);
        nomeMovimento.setText(movimento.getNome());
        EditText valorPr = viewCriada.findViewById(R.id.dialog_valor_pr);
        List<MovimentoComValor> listaDeMovimentos = movimentosDao.getMovimentosComPr();
        for (MovimentoComValor movimentoDaVez :
                listaDeMovimentos) {
            if (movimento.getIdMovimento() == movimentoDaVez.movimento.getIdMovimento()) {
                MovimentoComValor movimentoComValor = movimentoDaVez;
                if (!movimentoComValor.valoresDePRMovimento.isEmpty()) {
                    valorPr.setText(Double.toString(movimentoComValor.valoresDePRMovimento.get(movimentoComValor.valoresDePRMovimento.size() - 1).getValorPr()));
                }
            }
        }
    }


    private void buscaListaDeMovimentos() {

        new ListaMovimentosTask(movimentosDao, adapter).execute();
    }
}
