package br.com.alura.crossfitprs.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import br.com.alura.crossfitprs.R;

public class AlteraPrDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(getLayoutInflater().inflate(R.layout.dialog_view_teste, null))
                .setMessage("Mensagem")
                .setTitle("Titulo")
                .setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Positivo Clicado", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancela", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
}
