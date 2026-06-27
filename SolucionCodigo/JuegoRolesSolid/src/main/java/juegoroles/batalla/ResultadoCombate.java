package juegoroles.batalla;

import juegoroles.personaje.Combatiente;

public class ResultadoCombate {
    private final Combatiente ganador;
    private final Combatiente perdedor;

    public ResultadoCombate(Combatiente ganador, Combatiente perdedor) {
        this.ganador = ganador;
        this.perdedor = perdedor;
    }

    public Combatiente obtenerGanador() {
        return ganador;
    }

    public Combatiente obtenerPerdedor() {
        return perdedor;
    }

    @Override
    public String toString() {
        return String.format("Ganador: %s\nPerdedor: %s", ganador.obtenerNombre(), perdedor.obtenerNombre());
    }
}
