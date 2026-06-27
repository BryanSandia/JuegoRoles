package juegoroles.habilidad;

import juegoroles.personaje.PerfilPersonaje;

public class EspadaCuerpoACuerpo implements HabilidadAtaque {
    @Override
    public int usar(PerfilPersonaje usuario) {
        return 14 + usuario.obtenerNivel() * 3;
    }
}
