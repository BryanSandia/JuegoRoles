package juegoroles.personaje;

public interface Combatiente {
    String obtenerNombre();
    int obtenerVida();
    boolean estaVivo();
    int usarAtaque();
    void recibirDefensa(int danio);
    void ganarExperiencia(int xp);
    void subirNivel();
    String estado();
}
