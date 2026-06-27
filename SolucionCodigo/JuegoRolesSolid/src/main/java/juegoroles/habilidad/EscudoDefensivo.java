package juegoroles.habilidad;

import juegoroles.personaje.PerfilPersonaje;

public class EscudoDefensivo implements HabilidadDefensa {
    @Override
    public int proteger(int danio, PerfilPersonaje defensor) {
        int defensa = 5 + defensor.obtenerBonoDefensa();
        return Math.max(0, danio - defensa);
    }
}
