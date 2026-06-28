package juegoroles.estados;

import juegoroles.personaje.Combatiente;

public interface IEstadoAlterado {
    String obtenerNombre();
    String obtenerDescripcion();
    boolean bloqueaAccion();
    int modificarAtaque(int ataqueBase);
    int modificarDanioRecibido(int danio);
    void aplicarEfecto(Combatiente objetivo);
    void reducirDuracion();
    boolean estaActivo();
}
