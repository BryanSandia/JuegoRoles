package juegoroles.habilidad;

import juegoroles.personaje.PerfilPersonaje;

public class HechizoMagico implements HabilidadAtaque {
    @Override
    public int usar(PerfilPersonaje usuario) {
        return 18 + usuario.obtenerNivel() * 2;
    }
}
