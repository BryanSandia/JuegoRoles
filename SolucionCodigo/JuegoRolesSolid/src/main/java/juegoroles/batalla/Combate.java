package juegoroles.batalla;

import juegoroles.personaje.Combatiente;

public class Combate {
    public ResultadoCombate luchar(Combatiente primero, Combatiente segundo) {
        Combatiente atacante = primero;
        Combatiente defensor = segundo;

        while (atacante.estaVivo() && defensor.estaVivo()) {
            int danio = atacante.usarAtaque();
            defensor.recibirDefensa(danio);
            if (!defensor.estaVivo()) {
                atacante.ganarExperiencia(15);
                atacante.subirNivel();
                return new ResultadoCombate(atacante, defensor);
            }
            Combatiente temporal = atacante;
            atacante = defensor;
            defensor = temporal;
        }
        return new ResultadoCombate(atacante.estaVivo() ? atacante : defensor,
                atacante.estaVivo() ? defensor : atacante);
    }
}
