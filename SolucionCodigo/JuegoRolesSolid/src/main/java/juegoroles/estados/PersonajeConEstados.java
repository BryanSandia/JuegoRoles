package juegoroles.estados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import juegoroles.personaje.Personaje;

public class PersonajeConEstados extends Personaje {
    private final Personaje personajeBase;
    private final List<IEstadoAlterado> estadosActivos;

    public PersonajeConEstados(Personaje personajeBase) {
        super(personajeBase.obtenerNombre(), 0, personajeBase.obtenerNivel(), null, null, 0);
        this.personajeBase = personajeBase;
        this.estadosActivos = new ArrayList<>();
    }

    public void aplicarEstado(IEstadoAlterado estado) {
        estadosActivos.add(estado);
    }

    private void procesarEstadosActivos() {
        Iterator<IEstadoAlterado> iterador = estadosActivos.iterator();
        while (iterador.hasNext()) {
            IEstadoAlterado estado = iterador.next();
            estado.aplicarEfecto(personajeBase);
            if (!estado.estaActivo()) {
                iterador.remove();
            }
        }
    }

    private boolean tieneBloqueoActivo() {
        return estadosActivos.stream().anyMatch(IEstadoAlterado::bloqueaAccion);
    }

    private int aplicarModificadoresAtaque(int ataqueBase) {
        int resultado = ataqueBase;
        for (IEstadoAlterado estado : estadosActivos) {
            resultado = estado.modificarAtaque(resultado);
        }
        return resultado;
    }

    private int aplicarModificadoresDanio(int danio) {
        int resultado = danio;
        for (IEstadoAlterado estado : estadosActivos) {
            resultado = estado.modificarDanioRecibido(resultado);
        }
        return resultado;
    }

    @Override
    public int usarAtaque() {
        procesarEstadosActivos();
        if (!estaVivo()) {
            return 0;
        }
        if (tieneBloqueoActivo()) {
            System.out.println(obtenerNombre() + " no puede atacar debido a un estado alterado activo.");
            return 0;
        }
        int ataque = personajeBase.usarAtaque();
        return aplicarModificadoresAtaque(ataque);
    }

    @Override
    public void recibirDefensa(int danio) {
        int danioFinal = aplicarModificadoresDanio(danio);
        personajeBase.recibirDefensa(danioFinal);
    }

    @Override
    public String estado() {
        String estadoBase = personajeBase.estado();
        if (estadosActivos.isEmpty()) {
            return estadoBase;
        }
        String estadosDescripcion = estadosActivos.stream()
                .map(IEstadoAlterado::obtenerDescripcion)
                .collect(Collectors.joining(", "));
        return String.format("%s - Estados activos: [%s]", estadoBase, estadosDescripcion);
    }

    @Override
    public int obtenerVida() {
        return personajeBase.obtenerVida();
    }

    @Override
    public boolean estaVivo() {
        return personajeBase.estaVivo();
    }

    @Override
    public void ganarExperiencia(int xp) {
        personajeBase.ganarExperiencia(xp);
    }

    @Override
    public void subirNivel() {
        personajeBase.subirNivel();
    }

    @Override
    public int obtenerNivel() {
        return personajeBase.obtenerNivel();
    }

    @Override
    public int obtenerBonoDefensa() {
        return personajeBase.obtenerBonoDefensa();
    }

    @Override
    public int getEnergia() {
        return personajeBase.getEnergia();
    }

    @Override
    public int getCooldown() {
        return personajeBase.getCooldown();
    }

    @Override
    public void reducirCooldown() {
        personajeBase.reducirCooldown();
    }

    @Override
    public void usarHabilidadEspecial() throws Exception {
        personajeBase.usarHabilidadEspecial();
    }

    @Override
    public void habilidadEspecial() throws Exception {
        personajeBase.habilidadEspecial();
    }
}
