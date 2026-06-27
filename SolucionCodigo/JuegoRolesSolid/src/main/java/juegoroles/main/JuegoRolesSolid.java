package juegoroles.main;

import juegoroles.batalla.Combate;
import juegoroles.batalla.ResultadoCombate;
import juegoroles.creador.FabricaPersonajes;
import juegoroles.personaje.Guerrero;
import juegoroles.personaje.Mago;
import juegoroles.personaje.Arquero;
import juegoroles.inventario.Arma;
import juegoroles.inventario.Armadura;
import juegoroles.personaje.Combatiente;
import juegoroles.personaje.PersonajeEquipado;

public class JuegoRolesSolid {
    public static void main(String[] args) {
        Combatiente guerrero = FabricaPersonajes.crearGuerrero("Garrosh");
        Combatiente mago = FabricaPersonajes.crearMago("Jaina");
        Combatiente arquero = FabricaPersonajes.crearArquero("Legolas");
        
        PersonajeEquipado guerreroE = new PersonajeEquipado(guerrero);
        PersonajeEquipado magoE = new PersonajeEquipado(mago);
        PersonajeEquipado arqueroE = new PersonajeEquipado(arquero);

        Arma espada = new Arma("Espada de acero", 8, Guerrero.class);
        Arma baston = new Arma("Bastón de fuego", 12, Mago.class);
        Arma arco = new Arma("Arco largo", 6, Arquero.class);

        Armadura armaduraPesada = new Armadura("Armadura de placas", 5, Guerrero.class);
        Armadura tunicaMagica = new Armadura("Túnica encantada", 3, Mago.class);
        Armadura cuero = new Armadura("Armadura de cuero", 2, Arquero.class);

        guerreroE.agregarObjeto(espada);
        guerreroE.agregarObjeto(armaduraPesada);
        guerreroE.equiparArma(espada);
        guerreroE.equiparArmadura(armaduraPesada);

        magoE.agregarObjeto(baston);
        magoE.agregarObjeto(tunicaMagica);
        magoE.equiparArma(baston);
        magoE.equiparArmadura(tunicaMagica);

        arqueroE.agregarObjeto(arco);
        arqueroE.agregarObjeto(cuero);
        arqueroE.equiparArma(arco);
        arqueroE.equiparArmadura(cuero);

        Combate combate = new Combate();

        System.out.println("=== Batalla 1: Guerrero vs Mago ===");
        imprimirResultado(combate.luchar(guerreroE, magoE));

        System.out.println("\n=== Batalla 2: Mago vs Arquero ===");
        imprimirResultado(combate.luchar(magoE, arqueroE));

        System.out.println("\n=== Batalla 3: Guerrero vs Arquero ===");
        imprimirResultado(combate.luchar(guerreroE, arqueroE));
    }

    private static void imprimirResultado(ResultadoCombate resultado) {
        System.out.println(resultado);
        System.out.println(resultado.obtenerGanador().estado());
        System.out.println(resultado.obtenerPerdedor().estado());
    }
}
