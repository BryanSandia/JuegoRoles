package juegoroles.estados;

import juegoroles.personaje.Combatiente;

public class AumentarFuerza implements IEstadoAlterado {
    private int duracion;
    private final int bonusAtaque;

    public AumentarFuerza(int duracion, int bonusAtaque) {
        this.duracion = duracion;
        this.bonusAtaque = bonusAtaque;
    }

    @Override
    public String obtenerNombre() {
        return "Aumentar Fuerza";
    }

    @Override
    public String obtenerDescripcion() {
        return String.format("%s (+%d) (%d turnos)", obtenerNombre(), bonusAtaque, duracion);
    }

    @Override
    public boolean bloqueaAccion() {
        return false;
    }

    @Override
    public int modificarAtaque(int ataqueBase) {
        return estaActivo() ? ataqueBase + bonusAtaque : ataqueBase;
    }

    @Override
    public int modificarDanioRecibido(int danio) {
        return danio;
    }

    @Override
    public void aplicarEfecto(Combatiente objetivo) {
        if (!estaActivo()) {
            return;
        }
        System.out.printf("%s mantiene un aumento de fuerza de +%d este turno.%n", objetivo.obtenerNombre(), bonusAtaque);
        reducirDuracion();
    }

    @Override
    public void reducirDuracion() {
        if (duracion > 0) {
            duracion--;
        }
    }

    @Override
    public boolean estaActivo() {
        return duracion > 0;
    }
}
