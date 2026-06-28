package juegoroles.creador;


import juegoroles.estados.PersonajeConEstados;
import juegoroles.habilidad.DisparoPreciso;
import juegoroles.habilidad.EscudoDefensivo;
import juegoroles.habilidad.EspadaCuerpoACuerpo;
import juegoroles.habilidad.Evadir;
import juegoroles.habilidad.GolpePoderoso;
import juegoroles.habilidad.HabilidadAtaque;
import juegoroles.habilidad.HabilidadDefensa;
import juegoroles.habilidad.HabilidadEspecial;
import juegoroles.habilidad.HechizoMagico;
import juegoroles.habilidad.HechizoSupremo;
import juegoroles.habilidad.TiroPreciso;
import juegoroles.personaje.Arquero;
import juegoroles.personaje.Combatiente;
import juegoroles.personaje.Guerrero;
import juegoroles.personaje.Mago;
import juegoroles.personaje.Personaje;

public final class FabricaPersonajes {

    private FabricaPersonajes() {
    }

    public static Combatiente crearGuerrero(String nombre) {
        HabilidadAtaque ataque = new EspadaCuerpoACuerpo();
        HabilidadDefensa defensa = new EscudoDefensivo();
        HabilidadEspecial especial = new GolpePoderoso();

        Personaje guerrero = new Guerrero(nombre, ataque, defensa);
        return new PersonajeConEstados(guerrero);
    }

    public static Combatiente crearMago(String nombre) {
        HabilidadAtaque ataque = new HechizoMagico();
        HabilidadDefensa defensa = new EscudoDefensivo();
        HabilidadEspecial especial = new HechizoSupremo();

        Personaje mago = new Mago(nombre, ataque, defensa);
        return new PersonajeConEstados(mago);
    }

    public static Combatiente crearArquero(String nombre) {
        HabilidadAtaque ataque = new TiroPreciso();
        HabilidadDefensa defensa = new Evadir();
        HabilidadEspecial especial = new DisparoPreciso();

        Personaje arquero = new Arquero(nombre, ataque, defensa);
        return new PersonajeConEstados(arquero);
    }
}

