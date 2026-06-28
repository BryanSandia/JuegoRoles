package juegoroles.inventario;
import juegoroles.personaje.Combatiente;
import juegoroles.personaje.PersonajeEquipado;
public class Arma extends Objeto{
    private int bonusAtaque;
    private Class<? extends Combatiente> tipoPermitido;

    public Arma(String nombre,int bonusAtaque, Class<? extends Combatiente> tipoPermitido) {
        super(nombre);
        this.bonusAtaque = bonusAtaque;
        this.tipoPermitido = tipoPermitido;
    }
    
    @Override
    public int obtenerModificadorAtaque() {
        return bonusAtaque;
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
