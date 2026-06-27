package juegoroles.habilidad;

import juegoroles.personaje.PerfilPersonaje;

public class Evadir implements HabilidadDefensa {
    @Override
    public int proteger(int danio, PerfilPersonaje defensor) {
        int esquiva = defensor.obtenerNivel() % 2 == 0 ? 4 : 2;
        return Math.max(0, danio - esquiva);
    }
}
