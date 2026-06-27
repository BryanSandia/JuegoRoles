package juegoroles.main;

import juegoroles.batalla.Combate;
import juegoroles.batalla.ResultadoCombate;
import juegoroles.creador.FabricaPersonajes;
import juegoroles.personaje.Combatiente;

public class JuegoRolesSolid {
    public static void main(String[] args) {
        Combatiente guerrero = FabricaPersonajes.crearGuerrero("Garrosh");
        Combatiente mago = FabricaPersonajes.crearMago("Jaina");
        Combatiente arquero = FabricaPersonajes.crearArquero("Legolas");

        Combate combate = new Combate();

        System.out.println("=== Batalla 1: Guerrero vs Mago ===");
        imprimirResultado(combate.luchar(guerrero, mago));

        System.out.println("\n=== Batalla 2: Mago vs Arquero ===");
        imprimirResultado(combate.luchar(mago, arquero));

        System.out.println("\n=== Batalla 3: Guerrero vs Arquero ===");
        imprimirResultado(combate.luchar(guerrero, arquero));
    }

    private static void imprimirResultado(ResultadoCombate resultado) {
        System.out.println(resultado);
        System.out.println(resultado.obtenerGanador().estado());
        System.out.println(resultado.obtenerPerdedor().estado());
    }
}
