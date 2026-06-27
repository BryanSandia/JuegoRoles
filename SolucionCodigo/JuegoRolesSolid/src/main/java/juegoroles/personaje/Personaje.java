package juegoroles.personaje;

import juegoroles.habilidad.HabilidadAtaque;
import juegoroles.habilidad.HabilidadDefensa;

public abstract class Personaje implements Combatiente, PerfilPersonaje {
    private final String nombre;
    private int vida;
    private int vidaMaxima;
    private int experiencia;
    private int nivel;
    private final HabilidadAtaque habilidadAtaque;
    private final HabilidadDefensa habilidadDefensa;
    private int energia;
    private int cooldown;
    

    protected Personaje(String nombre, int vidaMaxima, int nivel,
                        HabilidadAtaque habilidadAtaque,
                        HabilidadDefensa habilidadDefensa, int energia) {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.nivel = nivel;
        this.experiencia = 0;
        this.energia = energia;
        this.cooldown = 0;
        this.habilidadAtaque = habilidadAtaque;
        this.habilidadDefensa = habilidadDefensa;
        
    }

    @Override
    public String obtenerNombre() {
        return nombre;
    }

    @Override
    public int obtenerVida() {
        return vida;
    }

    @Override
    public boolean estaVivo() {
        return vida > 0;
    }

    @Override
    public void ganarExperiencia(int xp) {
        experiencia += xp;
    }

    @Override
    public void subirNivel() {
        int umbral = nivel * 20;
        if (experiencia >= umbral) {
            experiencia -= umbral;
            nivel++;
            vidaMaxima += 10;
            vida = Math.min(vidaMaxima, vida + 10);
        }
    }

    @Override
    public String estado() {
        return String.format("%s [Nivel %d - Vida %d/%d - XP %d]",
                nombre, nivel, vida, vidaMaxima, experiencia);
    }

    @Override
    public int usarAtaque() {
        return habilidadAtaque.usar(this);
    }

    @Override
    public void recibirDefensa(int danio) {
        int danioFinal = habilidadDefensa.proteger(danio, this);
        recibirGolpe(danioFinal);
    }

    protected void recibirGolpe(int danio) {
        vida = Math.max(0, vida - danio);
    }

    @Override
    public int obtenerNivel() {
        return nivel;
    }

    @Override
    public int obtenerBonoDefensa() {
        return 1 + nivel;
    }
    public int getEnergia() {
    return energia;
}

public int getCooldown() {
    return cooldown;
}

public void reducirCooldown() {
    if (cooldown > 0) {
        cooldown--;
    }
}

public void usarHabilidadEspecial() throws Exception {

    if (energia < 20) {
        throw new Exception("No tienes suficiente energía.");
    }

    if (cooldown > 0) {
        throw new Exception("La habilidad está en cooldown.");
    }

    energia -= 20;
    cooldown = 3;
}
public abstract void habilidadEspecial() throws Exception;
}
