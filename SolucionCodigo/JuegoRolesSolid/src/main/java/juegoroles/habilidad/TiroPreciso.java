package juegoroles.habilidad;

import juegoroles.personaje.PerfilPersonaje;

public class TiroPreciso implements HabilidadAtaque {
    @Override
    public int usar(PerfilPersonaje usuario) {
        return 13 + usuario.obtenerNivel() * 3;
    }
}
