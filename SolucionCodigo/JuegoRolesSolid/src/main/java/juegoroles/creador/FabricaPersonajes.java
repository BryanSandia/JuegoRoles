package juegoroles.creador;

import juegoroles.habilidad.EscudoDefensivo;
import juegoroles.habilidad.EspadaCuerpoACuerpo;
import juegoroles.habilidad.Evadir;
import juegoroles.habilidad.HabilidadAtaque;
import juegoroles.habilidad.HabilidadDefensa;
import juegoroles.habilidad.HechizoMagico;
import juegoroles.habilidad.TiroPreciso;
import juegoroles.personaje.Arquero;
import juegoroles.personaje.Guerrero;
import juegoroles.personaje.Mago;
import juegoroles.personaje.Personaje;

public final class FabricaPersonajes {
    private FabricaPersonajes() {
    }

    public static Personaje crearGuerrero(String nombre) {
        HabilidadAtaque ataque = new EspadaCuerpoACuerpo();
        HabilidadDefensa defensa = new EscudoDefensivo();
        return new Guerrero(nombre, ataque, defensa);
    }

    public static Personaje crearMago(String nombre) {
        HabilidadAtaque ataque = new HechizoMagico();
        HabilidadDefensa defensa = new EscudoDefensivo();
        return new Mago(nombre, ataque, defensa);
    }

    public static Personaje crearArquero(String nombre) {
        HabilidadAtaque ataque = new TiroPreciso();
        HabilidadDefensa defensa = new Evadir();
        return new Arquero(nombre, ataque, defensa);
    }
}
