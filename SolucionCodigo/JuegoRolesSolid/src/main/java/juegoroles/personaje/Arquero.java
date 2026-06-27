package juegoroles.personaje;

import juegoroles.habilidad.HabilidadAtaque;
import juegoroles.habilidad.HabilidadDefensa;

public class Arquero extends Personaje {

    public Arquero(String nombre, HabilidadAtaque ataque, HabilidadDefensa defensa) {
        super(nombre, 100, 1, ataque, defensa, 100);
    }
    @Override
    public void habilidadEspecial() throws Exception {
        usarHabilidadEspecial();
        System.out.println(obtenerNombre() + " realiza Disparo Preciso.");
    }

}
