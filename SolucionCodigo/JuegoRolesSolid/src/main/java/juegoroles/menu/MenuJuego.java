package juegoroles.menu;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

import juegoroles.creador.FabricaPersonajes;
import juegoroles.estados.PersonajeConEstados;
import juegoroles.inventario.Arma;
import juegoroles.inventario.Armadura;
import juegoroles.inventario.Objeto;
import juegoroles.personaje.Arquero;
import juegoroles.personaje.Combatiente;
import juegoroles.personaje.Guerrero;
import juegoroles.personaje.Mago;
import juegoroles.personaje.Personaje;
import juegoroles.personaje.PersonajeEquipado;

public class MenuJuego {
    private static final Scanner entrada = new Scanner(System.in);

    public void iniciar() {
        System.out.println("=== Bienvenido al reino de los héroes ===");
        System.out.println("Crea tu personaje y desafía a otros aventureros.");

        PersonajeEquipado jugador = crearJugador();
        PersonajeEquipado oponente = crearOponente();

        boolean seguir = true;
        while (seguir) {
            mostrarMenu(jugador, oponente);
            int opcion = leerEntero("Elige una acción");

            switch (opcion) {
                case 1:
                    iniciarBatallaPorTurnos(jugador, oponente);
                    break;
                case 2:
                    mostrarEstado(jugador);
                    break;
                case 3:
                    mostrarEstado(oponente);
                    break;
                case 4:
                    usarPoderEspecial(jugador);
                    break;
                case 5:
                    gestionarInventario(jugador);
                    break;
                case 6:
                    jugador = crearJugador();
                    oponente = crearOponente();
                    break;
                case 0:
                    System.out.println("Has abandonado la aventura.");
                    seguir = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        entrada.close();
    }

    private PersonajeEquipado crearJugador() {
        String nombre = leerTexto("Nombre del personaje");
        int clase = leerEntero("Clase (1=Guerrero, 2=Mago, 3=Arquero)");

        Combatiente base = crearCombatientePorClase(clase, nombre);
        PersonajeEquipado personaje = new PersonajeEquipado(base);
        equiparEquipo(personaje, base);
        return personaje;
    }

    private PersonajeEquipado crearOponente() {
        String nombre = leerTexto("Nombre del rival");
        int clase = leerEntero("Clase rival (1=Guerrero, 2=Mago, 3=Arquero)");

        Combatiente base = crearCombatientePorClase(clase, nombre);
        PersonajeEquipado personaje = new PersonajeEquipado(base);
        equiparEquipo(personaje, base);
        return personaje;
    }

    private Combatiente crearCombatientePorClase(int clase, String nombre) {
        switch (clase) {
            case 1:
                return FabricaPersonajes.crearGuerrero(nombre);
            case 2:
                return FabricaPersonajes.crearMago(nombre);
            case 3:
                return FabricaPersonajes.crearArquero(nombre);
            default:
                System.out.println("Clase no válida. Se eligió Arquero por defecto.");
                return FabricaPersonajes.crearArquero(nombre);
        }
    }

    private void equiparEquipo(PersonajeEquipado personaje, Combatiente base) {
        Class<? extends Combatiente> tipo = detectarTipo(base);

        if (Guerrero.class.isAssignableFrom(tipo)) {
            Arma arma = new Arma("Espada de acero", 8, Guerrero.class);
            Armadura armadura = new Armadura("Armadura de placas", 5, Guerrero.class);
            personaje.agregarObjeto(arma);
            personaje.agregarObjeto(armadura);
            try {
                personaje.equiparArma(arma);
                personaje.equiparArmadura(armadura);
            } catch (IllegalArgumentException ex) {
                System.out.println("No se pudo equipar el equipo inicial: " + ex.getMessage());
            }
        } else if (Mago.class.isAssignableFrom(tipo)) {
            Arma arma = new Arma("Bastón de fuego", 12, Mago.class);
            Armadura armadura = new Armadura("Túnica encantada", 3, Mago.class);
            personaje.agregarObjeto(arma);
            personaje.agregarObjeto(armadura);
            try {
                personaje.equiparArma(arma);
                personaje.equiparArmadura(armadura);
            } catch (IllegalArgumentException ex) {
                System.out.println("No se pudo equipar el equipo inicial: " + ex.getMessage());
            }
        } else if (Arquero.class.isAssignableFrom(tipo)) {
            Arma arma = new Arma("Arco largo", 6, Arquero.class);
            Armadura armadura = new Armadura("Armadura de cuero", 2, Arquero.class);
            personaje.agregarObjeto(arma);
            personaje.agregarObjeto(armadura);
            try {
                personaje.equiparArma(arma);
                personaje.equiparArmadura(armadura);
            } catch (IllegalArgumentException ex) {
                System.out.println("No se pudo equipar el equipo inicial: " + ex.getMessage());
            }
        }
    }

    private Class<? extends Combatiente> detectarTipo(Combatiente personaje) {
        if (personaje instanceof Guerrero) {
            return Guerrero.class;
        }
        if (personaje instanceof Mago) {
            return Mago.class;
        }
        if (personaje instanceof Arquero) {
            return Arquero.class;
        }
        if (personaje instanceof PersonajeConEstados) {
            try {
                Field campoBase = PersonajeConEstados.class.getDeclaredField("personajeBase");
                campoBase.setAccessible(true);
                Object valor = campoBase.get(personaje);
                if (valor instanceof Guerrero) {
                    return Guerrero.class;
                }
                if (valor instanceof Mago) {
                    return Mago.class;
                }
                if (valor instanceof Arquero) {
                    return Arquero.class;
                }
            } catch (ReflectiveOperationException ex) {
                System.out.println("No se pudo identificar el tipo del personaje. Se usará el valor por defecto.");
            }
        }
        return Arquero.class;
    }

    private void mostrarMenu(PersonajeEquipado jugador, PersonajeEquipado oponente) {
        System.out.println("\n=== Menú de aventura ===");
        System.out.println("1. Enfrentar a " + oponente.obtenerNombre());
        System.out.println("2. Ver estado de tu héroe");
        System.out.println("3. Ver estado del rival");
        System.out.println("4. Usar poder especial");
        System.out.println("5. Gestionar inventario");
        System.out.println("6. Crear nuevo personaje y rival");
        System.out.println("0. Salir");
    }

    private void mostrarEstado(PersonajeEquipado personaje) {
        System.out.println(personaje.estado());
    }

    private void usarPoderEspecial(PersonajeEquipado personaje) {
        Combatiente base = personaje.obtenerPersonajeBase();
        if (base instanceof Personaje) {
            try {
                ((Personaje) base).usarHabilidadEspecial();
                System.out.println(personaje.obtenerNombre() + " activa su poder especial.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Este personaje no posee una habilidad especial disponible.");
        }
    }

    private void iniciarBatallaPorTurnos(PersonajeEquipado jugador, PersonajeEquipado oponente) {
        System.out.println("\n=== Comienza la batalla por turnos ===");
        System.out.println(jugador.obtenerNombre() + " se enfrenta a " + oponente.obtenerNombre());

        boolean turnoJugador = true;
        boolean defensaJugador = false;
        boolean defensaOponente = false;
        int turno = 1;

        while (jugador.estaVivo() && oponente.estaVivo()) {
            System.out.println("\n--- Turno " + turno + " ---");
            System.out.println(jugador.estado());
            System.out.println(oponente.estado());

            if (turnoJugador) {
                int accion = leerEntero("Tu turno: 1-Ataque, 2-Habilidad especial, 3-Defenderse, 4-Gestionar inventario");
                switch (accion) {
                    case 1:
                        atacar(jugador, oponente, defensaOponente);
                        defensaOponente = false;
                        break;
                    case 2:
                        usarHabilidadEnCombate(jugador, oponente);
                        break;
                    case 3:
                        defensaJugador = true;
                        System.out.println(jugador.obtenerNombre() + " se pone a la defensiva.");
                        break;
                    case 4:
                        gestionarInventario(jugador);
                        break;
                    default:
                        System.out.println("Acción no válida. Se tomará como ataque.");
                        atacar(jugador, oponente, defensaOponente);
                        defensaOponente = false;
                        break;
                }
            } else {
                int accionCpu = elegirAccionCpu();
                switch (accionCpu) {
                    case 1:
                        atacar(oponente, jugador, defensaJugador);
                        defensaJugador = false;
                        break;
                    case 2:
                        usarHabilidadEnCombate(oponente, jugador);
                        break;
                    case 3:
                        defensaOponente = true;
                        System.out.println(oponente.obtenerNombre() + " se pone a la defensiva.");
                        break;
                    default:
                        atacar(oponente, jugador, defensaJugador);
                        defensaJugador = false;
                        break;
                }
            }

            if (!jugador.estaVivo() || !oponente.estaVivo()) {
                break;
            }

            turnoJugador = !turnoJugador;
            turno++;
        }

        if (jugador.estaVivo() && !oponente.estaVivo()) {
            System.out.println("\n¡" + jugador.obtenerNombre() + " ha vencido a " + oponente.obtenerNombre() + "!");
        } else if (oponente.estaVivo() && !jugador.estaVivo()) {
            System.out.println("\n¡" + oponente.obtenerNombre() + " ha vencido a " + jugador.obtenerNombre() + "!");
        } else {
            System.out.println("\nLa batalla terminó en empate.");
        }
    }

    private void atacar(PersonajeEquipado atacante, PersonajeEquipado defensor, boolean defensorDefiende) {
        int danio = atacante.usarAtaque();
        if (defensorDefiende) {
            danio = Math.max(1, danio / 2);
            System.out.println(defensor.obtenerNombre() + " se defiende y recibe menos daño.");
        }
        defensor.recibirDefensa(danio);
        System.out.println(atacante.obtenerNombre() + " golpea a " + defensor.obtenerNombre() + " por " + danio + " de daño.");
    }

    private void usarHabilidadEnCombate(PersonajeEquipado atacante, PersonajeEquipado defensor) {
        Combatiente base = atacante.obtenerPersonajeBase();
        if (base instanceof Personaje) {
            try {
                ((Personaje) base).usarHabilidadEspecial();
                int danioEspecial = 12;
                defensor.recibirDefensa(danioEspecial);
                System.out.println(atacante.obtenerNombre() + " libera su poder especial y causa " + danioEspecial + " de daño.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Este personaje no tiene un poder especial listo.");
        }
    }

    private int elegirAccionCpu() {
        int aleatorio = (int) (Math.random() * 100);
        if (aleatorio < 50) {
            return 1;
        }
        if (aleatorio < 80) {
            return 2;
        }
        return 3;
    }

    private void gestionarInventario(PersonajeEquipado personaje) {
        List<Objeto> inventario = personaje.obtenerInventario();
        if (inventario.isEmpty()) {
            System.out.println("No tienes objetos en el inventario.");
            return;
        }

        System.out.println("\nInventario de " + personaje.obtenerNombre() + ":");
        for (int i = 0; i < inventario.size(); i++) {
            Objeto objeto = inventario.get(i);
            System.out.println((i + 1) + ". " + objeto.getNombre());
        }

        int opcion = leerEntero("Elige un objeto para equipar (0 para cancelar)");
        if (opcion <= 0 || opcion > inventario.size()) {
            System.out.println("No se cambió el equipo.");
            return;
        }

        Objeto seleccionado = inventario.get(opcion - 1);
        if (seleccionado instanceof Arma) {
            personaje.equiparArma((Arma) seleccionado);
            System.out.println("Equipaste el arma: " + seleccionado.getNombre());
        } else if (seleccionado instanceof Armadura) {
            personaje.equiparArmadura((Armadura) seleccionado);
            System.out.println("Equipaste la armadura: " + seleccionado.getNombre());
        } else {
            System.out.println("Ese objeto no puede equiparse.");
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje + ": ");
        return entrada.nextLine();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje + ": ");
        while (!entrada.hasNextInt()) {
            System.out.println("Entrada inválida. Debe ser un número.");
            entrada.next();
            System.out.print(mensaje + ": ");
        }
        int valor = entrada.nextInt();
        entrada.nextLine();
        return valor;
    }
}
