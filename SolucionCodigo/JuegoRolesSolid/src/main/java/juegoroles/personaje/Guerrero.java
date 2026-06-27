package juegoroles.personaje;

import juegoroles.habilidad.HabilidadAtaque;
import juegoroles.habilidad.HabilidadDefensa;

public class Guerrero extends Personaje {

    public Guerrero(String nombre, HabilidadAtaque ataque, HabilidadDefensa defensa) {
        super(nombre, 130, 1, ataque, defensa);
    }
}
