package es.studium.myavatar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class Nombre extends DialogFragment
{
    MostrarInfo listener;
    MainActivity main;
    EditText nombreAvatar;
    Genero gen;

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
        View myView = inflater.inflate(R.layout.dialogo_nombre, null);
        builder.setView(myView);

        nombreAvatar = myView.findViewById(R.id.txtNombre);

        builder.setTitle(R.string.dlgNombre)
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
                    if (nombreAvatar.getText().toString().isEmpty())
                    {
                        // Mostrar mensaje de error si el campo está vacío
                        Toast.makeText(getActivity(), R.string.error_nombre, Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        // Si no está vacío, notificar al listener y proceder
                        listener.establecerNombre(nombreAvatar.getText().toString());

                        Genero gen = new Genero();
                        gen.setCancelable(false);
                        gen.show(getActivity().getSupportFragmentManager(), "Genero");
                        dialog.dismiss();
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

        return dialog;
    }
}
