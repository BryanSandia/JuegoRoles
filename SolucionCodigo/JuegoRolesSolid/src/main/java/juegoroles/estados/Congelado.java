package juegoroles.estados;

import juegoroles.personaje.Combatiente;

public class Congelado implements IEstadoAlterado {
    private int duracion;

    public Congelado(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public String obtenerNombre() {
        return "Congelado";
    }

    @Override
    public String obtenerDescripcion() {
        return String.format("%s (%d turnos)", obtenerNombre(), duracion);
    }

    @Override
    public boolean bloqueaAccion() {
        return duracion > 0;
    }

    @Override
    public int modificarAtaque(int ataqueBase) {
        return ataqueBase;
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
        System.out.printf("%s está congelado y no puede atacar este turno.%n", objetivo.obtenerNombre());
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
