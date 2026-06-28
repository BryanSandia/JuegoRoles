package juegoroles.inventario;
import juegoroles.personaje.Combatiente;
import juegoroles.personaje.PersonajeEquipado;
public class Armadura extends Objeto{
    private int bonusDefensa;
    private Class<? extends Combatiente> tipoPermitido;

    public Armadura( String nombre,int bonusDefensa, Class<? extends Combatiente> tipoPermitido) {
        super(nombre);
        this.bonusDefensa = bonusDefensa;
        this.tipoPermitido = tipoPermitido;
    }
    
    @Override
    public int obtenerModificadorDefensa() {
        return bonusDefensa;
    }
    
    @Override
    public boolean puedeSerEquipadoPor(Combatiente personaje) {
        Combatiente objetivo = personaje;
        while (objetivo instanceof PersonajeEquipado) {
            objetivo = ((PersonajeEquipado) objetivo).obtenerPersonajeBase();
        }
        return tipoPermitido.isAssignableFrom(objetivo.getClass());
    }
}
