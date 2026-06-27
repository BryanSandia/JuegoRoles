package juegoroles.personaje;
import juegoroles.inventario.Arma;
import juegoroles.inventario.Armadura;
import juegoroles.inventario.Objeto;
import java.util.ArrayList;
import java.util.List;
public class PersonajeEquipado implements Combatiente {
    private final Combatiente personaje;
    private final List<Objeto> inventario;
    private Arma armaEquipada;
    private Armadura armaduraEquipada;

    public PersonajeEquipado(Combatiente personaje) {
        this.personaje = personaje;
        this.inventario = new ArrayList<>();
    }
    
     public void agregarObjeto(Objeto obj) {
        inventario.add(obj);
    }

    public void equiparArma(Arma arma) {
        if (!inventario.contains(arma)) {
            throw new IllegalArgumentException("El arma no está en el inventario");
        }
        if (!arma.puedeSerEquipadoPor(personaje)) {
            throw new IllegalArgumentException("Este personaje no puede equipar esta arma");
        }
        this.armaEquipada = arma;
    }

    public void equiparArmadura(Armadura armadura) {
        if (!inventario.contains(armadura)) {
            throw new IllegalArgumentException("La armadura no está en el inventario");
        }
        if (!armadura.puedeSerEquipadoPor(personaje)) {
            throw new IllegalArgumentException("Este personaje no puede equipar esta armadura");
        }
        this.armaduraEquipada = armadura;
    }

    public void desequiparArma() {
        armaEquipada = null;
    }

    public void desequiparArmadura() {
        armaduraEquipada = null;
    }

    public Combatiente obtenerPersonajeBase(){
        return personaje;
    }
    
    @Override
    public String obtenerNombre() {
        return personaje.obtenerNombre();
    }

    @Override
    public int obtenerVida() {
        return personaje.obtenerVida();
    }

    @Override
    public boolean estaVivo() {
        return personaje.estaVivo();
    }

    @Override
    public int usarAtaque() {
        int base = personaje.usarAtaque();
        int bonus = (armaEquipada != null) ? armaEquipada.obtenerModificadorAtaque() : 0;
        return base + bonus;
    }

    @Override
    public void recibirDefensa(int danio) {
        int reduccion = (armaduraEquipada != null) ? armaduraEquipada.obtenerModificadorDefensa() : 0;
        int danioReducido = Math.max(0, danio - reduccion);
        personaje.recibirDefensa(danioReducido);
    }

    @Override
    public void ganarExperiencia(int xp) {
        personaje.ganarExperiencia(xp);
    }

    @Override
    public void subirNivel() {
        personaje.subirNivel();
    }

    @Override
    public String estado() {
        return personaje.estado();
    }
    
    
}
