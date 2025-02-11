package es.studium.myavatar;

public interface Interfaz
{
    public void ocultarCaracteristicas();
    public void establecerNombre(String nombre);
    public void establecerGenero(String genero);
    public void establecerEspecie(String especie);
    public void establecerProfesion(String profesion);
    public void mostrarAtributos();
    public void mostrarAvatar(String genero, String especie, String profesion);
}