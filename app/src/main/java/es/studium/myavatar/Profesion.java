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

public class Profesion extends DialogFragment
{
    private Interfaz listener;
    private MainActivity main;

    RadioButton Arquero;
    RadioButton Guerrero;
    RadioButton Mago;
    RadioButton Herrero;
    RadioButton Minero;

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

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Construir el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View myView = inflater.inflate(R.layout.dialogo_profesion, null);
        builder.setView(myView);

        Arquero = myView.findViewById(R.id.rdArquero);
        Guerrero = myView.findViewById(R.id.rdGuerrero);
        Mago = myView.findViewById(R.id.rdMago);
        Herrero = myView.findViewById(R.id.rdHerrero);
        Minero = myView.findViewById(R.id.rdMinero);

        // Agrupamos los botones en un array para manejarlos de forma más sencilla
        RadioButton[] radioButtons = {Arquero, Guerrero, Mago, Herrero, Minero};

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

        builder.setTitle(R.string.dlgProfesion)
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
                    String genero = main.genero.getText().toString().split(": ")[1];
                    String especie = main.especie.getText().toString().split(": ")[1];

                    if(Arquero.isChecked())
                    {
                        mostrarTodo(listener, Arquero.getText().toString(), genero, especie, () -> dialog.dismiss());
                    }
                    else if(Guerrero.isChecked())
                    {
                        mostrarTodo(listener, Guerrero.getText().toString(), genero, especie, () -> dialog.dismiss());
                    }
                    else if(Mago.isChecked())
                    {
                        mostrarTodo(listener, Mago.getText().toString(), genero, especie, () -> dialog.dismiss());
                    }
                    else if(Herrero.isChecked())
                    {
                        mostrarTodo(listener, Herrero.getText().toString(), genero, especie, () -> dialog.dismiss());
                    }
                    else if(Minero.isChecked())
                    {
                        mostrarTodo(listener, Minero.getText().toString(), genero, especie, () -> dialog.dismiss());
                    }
                    else
                    {
                        Toast.makeText(getActivity(), R.string.error_profesion, Toast.LENGTH_SHORT).show();
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

    public void mostrarTodo(Interfaz listener, String profesion, String genero, String especie, Runnable onComplete)
    {
        // Validaciones de seguridad
        if (listener == null ||  genero == null || especie == null || profesion == null || onComplete == null)
        {
            throw new IllegalArgumentException("Parámetros inválidos en mostrarTodo.");
        }

        // Llamadas a las funciones internas
        listener.establecerProfesion(profesion);
        listener.mostrarAtributos();
        listener.mostrarAvatar(genero, especie, profesion);

        // Acción final (en lugar de acoplar a AlertDialog directamente)
        onComplete.run();
    }

}
