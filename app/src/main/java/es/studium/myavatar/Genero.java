package es.studium.myavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.Toast;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class Genero extends DialogFragment
{
    Interfaz listener;
    MainActivity main;
    RadioButton Mas;
    RadioButton Fem;

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        try
        {
            // Verifica que el contexto implemente la interfaz
            listener = (Interfaz) context;
            main = (MainActivity) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " debe implementar Interfaz");
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedIntanceState)
    {
        // Construir el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View myView = inflater.inflate(R.layout.dialogo_genero, null);
        builder.setView(myView);

        Mas = myView.findViewById(R.id.rdMas);
        Fem = myView.findViewById(R.id.rdFem);

        builder.setTitle(R.string.dlgGenero)
                .setPositiveButton(R.string.btnAceptar, null) // Creación del botón positivo, sin funcionalidad aún
                .setNegativeButton(R.string.btnCancelar, (dialog, which) -> {
                    Toast.makeText(getActivity(), R.string.error_cancelar, Toast.LENGTH_SHORT).show();
                    main.btnVolver.setVisibility(View.VISIBLE);
                });

        AlertDialog dialog = builder.create();

        // Configuración manual del botón positivo
        dialog.setOnShowListener(d -> {
            // Agregar manualmente el comportamiento del botón "Aceptar"
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                    try
                    {
                        if(Mas.isChecked())
                        {
                            listener.establecerGenero(Mas.getText().toString());

                            mostrarDialogoEspecie(dialog);
                        }
                        else if(Fem.isChecked())
                        {
                            listener.establecerGenero(Fem.getText().toString());

                            mostrarDialogoEspecie(dialog);
                        }
                        else
                        {
                            Toast.makeText(getActivity(), R.string.error_genero, Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch(Exception ex)
                    {
                        Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
            });
        });

        return dialog;
    }

    public void mostrarDialogoEspecie(AlertDialog dlg)
    {
        Especie esp = new Especie();
        esp.setCancelable(false);
        esp.show(getActivity().getSupportFragmentManager(), "Especie");
        dlg.dismiss();
    }
}
