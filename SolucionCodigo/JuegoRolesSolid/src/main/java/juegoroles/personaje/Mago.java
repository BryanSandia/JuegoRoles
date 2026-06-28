package juegoroles.personaje;

import juegoroles.habilidad.HabilidadAtaque;
import juegoroles.habilidad.HabilidadDefensa;

public class Mago extends Personaje {

    public Mago(String nombre, HabilidadAtaque ataque, HabilidadDefensa defensa) {
        super(nombre, 90, 1, ataque, defensa,100);
    }
    @Override
    public void habilidadEspecial() throws Exception {
        usarHabilidadEspecial();
        System.out.println(obtenerNombre() + " lanza Hechizo Supremo.");
    }
}
