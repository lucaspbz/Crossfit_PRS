package br.com.alura.crossfitprs.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.crossfitprs.R;
import br.com.alura.crossfitprs.dao.MovimentosDao;
import br.com.alura.crossfitprs.database.ListaMovimentosDatabase;
import br.com.alura.crossfitprs.model.Movimento;
import br.com.alura.crossfitprs.model.MovimentoComValor;
import br.com.alura.crossfitprs.model.ValorPr;

public class ListaMovimentosAdapter extends BaseAdapter {

    private final List<Movimento> movimentos = new ArrayList<>();
    private final MovimentosDao movimentosDao;
    OnItemClickListener onItemClickListener;
    private Context context;

    public ListaMovimentosAdapter(Context context) {
        this.context = context;
        movimentosDao = ListaMovimentosDatabase.getInstance(context).getMovimentosDao();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return movimentos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return movimentos.get(posicao);
    }

    @Override
    public long getItemId(int position) {
        return movimentos.get(position).getIdMovimento();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movimento movimentoEscolhido = movimentos.get(position);
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.movimento_lista, parent, false);
        TextView nome = viewCriada.findViewById(R.id.movimento_item_nome);
        nome.setText(movimentoEscolhido.getNome());
        TextView valorPr = viewCriada.findViewById(R.id.movimento_item_valor_pr);
        List<MovimentoComValor> movimentosComPr = movimentosDao.getMovimentosComPr();
        MovimentoComValor movimentoComValor = movimentosComPr.get(movimentoEscolhido.getIdMovimento()-1);
        if (!movimentoComValor.valoresDePRMovimento.isEmpty()) {
            ValorPr valorPrSalva = movimentoComValor.ultimaPr();
            valorPr.setText(Double.toString(valorPrSalva.getValorPr()) + " Kg");
        }

        valorPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(movimentoEscolhido);
            }
        });
        viewCriada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(movimentoEscolhido);
            }
        });
        return viewCriada;
    }


    private double formataValorPr(Editable s) {
        String str = s.toString();
        str = str.replaceAll("\\D+", "");
        return Double.parseDouble(str);
    }

    public void adiciona(List<Movimento> movimentosNovos) {
        movimentos.addAll(movimentosNovos);
    }

    public void atualiza(List<Movimento> movimentosAtualizados) {
        this.movimentos.clear();
        this.movimentos.addAll(movimentosAtualizados);
        notifyDataSetChanged();
        Toast.makeText(context, "Atualizando database", Toast.LENGTH_SHORT).show();
    }
}

