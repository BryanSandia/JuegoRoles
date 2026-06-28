package juegoroles.estados;

import juegoroles.personaje.Combatiente;

public class Envenenado implements IEstadoAlterado {
    private int duracion;
    private final int danioPorTurno;

    public Envenenado(int duracion, int danioPorTurno) {
        this.duracion = duracion;
        this.danioPorTurno = danioPorTurno;
    }

    @Override
    public String obtenerNombre() {
        return "Envenenado";
    }

    @Override
    public String obtenerDescripcion() {
        return String.format("%s (%d turnos)", obtenerNombre(), duracion);
    }

    @Override
    public boolean bloqueaAccion() {
        return false;
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
        System.out.printf("%s sufre %d de daño por envenenamiento.%n", objetivo.obtenerNombre(), danioPorTurno);
        objetivo.recibirDefensa(danioPorTurno);
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
