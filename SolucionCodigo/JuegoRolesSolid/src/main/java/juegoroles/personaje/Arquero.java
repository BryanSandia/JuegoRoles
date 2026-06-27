package juegoroles.personaje;

import juegoroles.habilidad.HabilidadAtaque;
import juegoroles.habilidad.HabilidadDefensa;

public class Arquero extends Personaje {

    public Arquero(String nombre, HabilidadAtaque ataque, HabilidadDefensa defensa) {
        super(nombre, 100, 1, ataque, defensa);
    }
}
