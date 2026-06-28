
package juegoroles.habilidad;


    public class HechizoSupremo implements HabilidadEspecial {

    private int energia = 100;
    private int cooldown = 0;

    @Override
    public void usar() throws Exception {

        if (energia < 20) {
            throw new Exception("No hay suficiente energía.");
        }

        if (cooldown > 0) {
            throw new Exception("La habilidad está en cooldown.");
        }

        energia -= 20;
        cooldown = 3;

        System.out.println("Hechizo Supremo ejecutado.");
    }

    public void reducirCooldown() {
        if (cooldown > 0) {
            cooldown--;
        }
    }
    
}
