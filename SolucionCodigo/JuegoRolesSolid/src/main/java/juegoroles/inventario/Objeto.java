package juegoroles.inventario;
import juegoroles.personaje.Combatiente;
public abstract class Objeto {
    protected String nombre;

    public Objeto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int obtenerModificadorAtaque() {
        return 0;
    }

    public int obtenerModificadorDefensa() {
        return 0;
    }
    public abstract boolean puedeSerEquipadoPor(Combatiente personaje);
}
