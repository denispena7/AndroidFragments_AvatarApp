package es.studium.myavatar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements MostrarInfo, View.OnClickListener
{
    Nombre nm;

    int imgAvatar = 0;
    ImageView avatar;
    TextView nombre;
    TextView genero;
    TextView especie;
    TextView profesion;
    TextView vida;
    TextView magia;
    TextView fuerza;
    TextView velocidad;
    Button btnReiniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avatar = findViewById(R.id.imgAvatar);

        nombre = findViewById(R.id.lblNombre);
        genero = findViewById(R.id.lblGenero);
        especie = findViewById(R.id.lblEspecie);
        profesion = findViewById(R.id.lblProfesion);

        vida = findViewById(R.id.lblVida);
        magia = findViewById(R.id.lblMagia);
        fuerza = findViewById(R.id.lblFuerza);
        velocidad = findViewById(R.id.lblVelocidad);

        btnReiniciar = findViewById(R.id.btnReiniciar);
        btnReiniciar.setOnClickListener(this);

        ocultarCaracteristicas();

        nm = new Nombre();
        nm.setCancelable(false);
        nm.show(getSupportFragmentManager(), "Nombre");
    }

    @Override
    public void ocultarCaracteristicas()
    {
        avatar.setVisibility(View.GONE);
        nombre.setVisibility(View.GONE);
        genero.setVisibility(View.GONE);
        especie.setVisibility(View.GONE);
        profesion.setVisibility(View.GONE);
        vida.setVisibility(View.GONE);
        magia.setVisibility(View.GONE);
        fuerza.setVisibility(View.GONE);
        velocidad.setVisibility(View.GONE);
        btnReiniciar.setVisibility(View.GONE);
    }

    @Override
    public void establecerNombre(String nombreAv)
    {
        String name = getString(R.string.nombre, nombreAv);
        nombre.setText(name);
    }

    @Override
    public void establecerGenero(String generoAv)
    {
        String gen = getString(R.string.genero, generoAv);
        genero.setText(gen);
    }

    @Override
    public void establecerEspecie(String especieAv)
    {
        String esp = getString(R.string.especie, especieAv);
        especie.setText(esp);
    }

    @Override
    public void establecerProfesion(String profesionAv)
    {
        String prof = getString(R.string.profesion, profesionAv);
        profesion.setText(prof);
    }

    @Override
    public void mostrarAtributos()
    {
        nombre.setVisibility(View.VISIBLE);
        genero.setVisibility(View.VISIBLE);
        especie.setVisibility(View.VISIBLE);
        profesion.setVisibility(View.VISIBLE);

        Random rnd = new Random();

        String valorVida = getString(R.string.vida, rnd.nextInt(100));
        vida.setText(valorVida);
        vida.setVisibility(View.VISIBLE);

        String valorMagia = getString(R.string.magia, rnd.nextInt(10));
        magia.setText(valorMagia);
        magia.setVisibility(View.VISIBLE);

        String valorFuerza = getString(R.string.fuerza, rnd.nextInt(20));
        fuerza.setText(valorFuerza);
        fuerza.setVisibility(View.VISIBLE);

        String valorVelocidad = getString(R.string.velocidad, rnd.nextInt(5));
        velocidad.setText(valorVelocidad);
        velocidad.setVisibility(View.VISIBLE);

        btnReiniciar.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarAvatar(String genero, String especie, String profesion)
    {
        // Construir la combinación clave de recurso en base a especie, profesión y género.
        switch (especie + "_" + profesion) {
            case "Elfo_Arquero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_archer_elf : R.drawable.female_archer_elf;
                break;
            case "Elfo_Guerrero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_warrior_elf : R.drawable.female_warrior_elf;
                break;
            case "Elfo_Mago":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_wizard_elf : R.drawable.female_wizard_elf;
                break;
            case "Elfo_Herrero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_blacksmith_elf : R.drawable.female_blacksmith_elf;
                break;
            case "Elfo_Minero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_miner_elf : R.drawable.female_miner_elf;
                break;

            case "Enano_Arquero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_archer_dwarf : R.drawable.female_archer_dwarf;
                break;
            case "Enano_Guerrero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_warrior_dwarf : R.drawable.female_warrior_dwarf;
                break;
            case "Enano_Mago":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_wizard_dwarf : R.drawable.female_wizard_dwarf;
                break;
            case "Enano_Herrero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_blacksmith_dwarf : R.drawable.female_blacksmith_dwarf;
                break;
            case "Enano_Minero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_miner_dwarf : R.drawable.female_miner_dwarf;
                break;

            case "Hobbit_Arquero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_archer_hobbit : R.drawable.female_archer_hobbit;
                break;
            case "Hobbit_Guerrero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_warrior_hobbit : R.drawable.female_warrior_hobbit;
                break;
            case "Hobbit_Mago":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_wizard_hobbit : R.drawable.female_wizard_hobbit;
                break;
            case "Hobbit_Herrero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_blacksmith_hobbit : R.drawable.female_blacksmith_hobbit;
                break;
            case "Hobbit_Minero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_miner_hobbit : R.drawable.female_miner_hobbit;
                break;

            case "Humano_Arquero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_archer_human : R.drawable.female_archer_human;
                break;
            case "Humano_Guerrero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_warrior_human : R.drawable.female_warrior_human;
                break;
            case "Humano_Mago":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_wizard_human : R.drawable.female_wizard_human;
                break;
            case "Humano_Herrero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_blacksmith_human : R.drawable.female_blacksmith_human;
                break;
            case "Humano_Minero":
                imgAvatar = genero.equals("Hombre") ? R.drawable.male_miner_human : R.drawable.female_miner_human;
                break;
            default:
                Toast.makeText(this, "No has seleccionado algo", Toast.LENGTH_SHORT).show();
        }

        // Asignación del recurso al ImageView.
        avatar.setImageResource(imgAvatar);
        avatar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.btnReiniciar)
        {
            ocultarCaracteristicas();
            nm = new Nombre();
            nm.setCancelable(false);
            nm.show(getSupportFragmentManager(), "Nombre");
        }
    }
}