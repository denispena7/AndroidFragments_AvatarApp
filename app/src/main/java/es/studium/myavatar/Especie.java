package es.studium.myavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class Especie extends DialogFragment
{
    Interfaz listener;
    MainActivity main;
    RadioButton esElfo;
    RadioButton esEnano;
    RadioButton esHobbit;
    RadioButton esHumano;

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
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View myView = inflater.inflate(R.layout.dialogo_especie, null);
        builder.setView(myView);

        esElfo = myView.findViewById(R.id.rdElfo);
        esEnano = myView.findViewById(R.id.rdEnano);
        esHobbit = myView.findViewById(R.id.rdHobbit);
        esHumano = myView.findViewById(R.id.rdHumano);

        // Agrupamos los botones en un array para manejarlos de forma más sencilla
        RadioButton[] radioButtons = {esElfo, esEnano, esHobbit, esHumano};

        // Configuramos un OnClickListener para cada RadioButton
        for (RadioButton radioButton : radioButtons)
        {
            radioButton.setOnClickListener(v -> {
                // Cuando se selecciona un RadioButton, deseleccionar los demás
                for (RadioButton rb : radioButtons)
                {
                    rb.setChecked(rb == radioButton); // Solo el clicado permanece seleccionado
                }
            });
        }

        builder.setTitle(R.string.dlgEspecie)
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
                        if(esElfo.isChecked())
                        {
                            listener.establecerEspecie(esElfo.getText().toString());

                            mostrarDialogoProfesion(dialog);
                        }
                        else if(esEnano.isChecked())
                        {
                            listener.establecerEspecie(esEnano.getText().toString());

                            mostrarDialogoProfesion(dialog);
                        }
                        else if(esHobbit.isChecked())
                        {
                            listener.establecerEspecie(esHobbit.getText().toString());

                            mostrarDialogoProfesion(dialog);
                        }
                        else if(esHumano.isChecked())
                        {
                            listener.establecerEspecie(esHumano.getText().toString());

                            mostrarDialogoProfesion(dialog);
                        }
                        else
                        {
                            Toast.makeText(getActivity(), R.string.error_especie, Toast.LENGTH_SHORT).show();
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

    public void mostrarDialogoProfesion(AlertDialog dlg)
    {
        Profesion pro = new Profesion();
        pro.setCancelable(false);
        pro.show(getActivity().getSupportFragmentManager(), "Profesión");
        dlg.dismiss();
    }
}
