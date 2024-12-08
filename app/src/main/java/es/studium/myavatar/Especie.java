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
    MostrarInfo listener;
    MainActivity main;
    RadioButton espElfo;
    RadioButton espEnano;
    RadioButton espHobbit;
    RadioButton espHumano;

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        try
        {
            // Verifica que el contexto implemente la interfaz
            listener = (MostrarInfo) context;
            main = (MainActivity) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + " debe implementar MostrarInfo");
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

        espElfo = myView.findViewById(R.id.rdElfo);
        espEnano = myView.findViewById(R.id.rdEnano);
        espHobbit = myView.findViewById(R.id.rdHobbit);
        espHumano = myView.findViewById(R.id.rdHumano);

        // Agrupamos los botones en un array para manejarlos de forma más sencilla
        RadioButton[] radioButtons = {espElfo, espEnano, espHobbit, espHumano};

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
                    main.btnReiniciar.setVisibility(View.VISIBLE);
                });

        AlertDialog dialog = builder.create();

        // Configuración manual del botón positivo
        dialog.setOnShowListener(d -> {
            // Agregar manualmente el comportamiento del botón "Aceptar"
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                    try
                    {
                        if(espElfo.isChecked())
                        {
                            listener.establecerEspecie(espElfo.getText().toString());

                            mostrarDialogoProfesion(dialog);
                        }
                        else if(espEnano.isChecked())
                        {
                            listener.establecerEspecie(espEnano.getText().toString());

                            mostrarDialogoProfesion(dialog);
                        }
                        else if(espHobbit.isChecked())
                        {
                            listener.establecerEspecie(espHobbit.getText().toString());

                            mostrarDialogoProfesion(dialog);
                        }
                        else if(espHumano.isChecked())
                        {
                            listener.establecerEspecie(espHumano.getText().toString());

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
