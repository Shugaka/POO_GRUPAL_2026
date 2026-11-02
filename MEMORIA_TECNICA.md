# Memoria técnica — Agencia de Viajes en el Tiempo

**Materia:** Paradigma Orientado a Objetos (UADE) · **Docente:** Pablo Hernández · **Grupo 2 (HDL)**

## 1. Descripción del sistema

El jugador trabaja para una agencia que viaja en el tiempo. Elige una **época**, viaja con la **máquina temporal**
(gasta energía), juega una **misión** (investigación de detective o nivel de plataformas) y, según lo que decida,
la misión genera **consecuencias** que modifican la **línea temporal**. Todo queda registrado como **eventos** y en el
historial del jugador. Una misión completada sube de dificultad.

Se puede jugar por consola (`Main`) o con interfaz gráfica Swing (`MainGUI`).

## 2. Cómo ejecutarlo

- Código fuente en `AgenciaTemporal/src` (marcar como *Sources Root* en IntelliJ).
- Consola: ejecutar `Main`. Interfaz gráfica: ejecutar `MainGUI`.
- Compilación por terminal: `javac -encoding UTF-8 -d out src/*.java` y luego `java -cp out MainGUI`.

## 3. Diagramas UML

- Diagrama de clases: `Agencia_de_viaje.drawio` (pestaña de clases, incluye tabla GRASP/SOLID).
- Diagramas de secuencia: jugar una misión (versión simplificada y versión completa), `.drawio`.
- Diagrama de flujo del recorrido del jugador (`Diagrama_de_flujo_mision.drawio`).

## 4. Dónde se aplica cada tema de la guía

### Unidad 1 — POO y UML
| Tema | Dónde |
|---|---|
| Clases, objetos, atributos, métodos | Todas las clases de `src` (ej. `Jugador`, `Epoca`, `MaquinaTemporal`) |
| Relaciones (cardinalidad, composición) | `AgenciaTemporal` ◆ `LineaTemporal`, `Epoca` ◆ `Mision`, `LineaTemporal` ◆ `Consecuencia`/`Evento` (ver diagrama de clases) |
| Constructores | Todas las clases tienen constructor; ej. `Jugador(String, MaquinaTemporal)`, `Epoca(String, int)` |
| Destructores | Java no tiene destructores (el recolector de basura libera la memoria). Para liberar recursos explícitamente se usa `Scanner.close()` en `Main` y `RecargaAutomatica.detener()` para el hilo |
| Tipos primitivos | `int energia`, `boolean viajando`, `int dificultad` (`MaquinaTemporal`, `Mision`) |
| Tipos wrapper | `Jugador.vecesJugada` es un `Map<String, Integer>`; se usa autoboxing en `registrarMision` (`merge(..., 1, Integer::sum)`) y auto-unboxing en `getVecesJugada` |
| Diagrama de clases y de secuencia | Sección 3 |

### Unidad 2 — Funcionamiento del lenguaje
| Tema | Dónde |
|---|---|
| Arreglos | `MisionDetective.SOSPECHOSOS` y `MisionDetective.PISTAS` (arrays de `String`); parámetro variable `String... opciones` en `EntradaUsuario.elegir` |
| Casteo | `Mision.crearConsecuencia` → `(int) Math.round(...)`; `MisionDetective` (constructor) → `(int) (Math.random() * ...)`; `VentanaPrincipal.ConsolaOutputStream` → `(char) b` |
| Tipos primitivos y de referencia | Ver tabla anterior y `List<Mision>`, `Epoca`, etc. |

### Unidad 3 — Clases y objetos
| Tema | Dónde |
|---|---|
| Herencia | `Mision` → `MisionDetective` y `MisionPlataformas`; `VentanaPrincipal extends JFrame` |
| Sobrecarga de constructores | `Mision(String, int, String)` y `Mision(String, String)` (este usa `this(...)`) |
| Sobrecarga de métodos | `MaquinaTemporal.recargarEnergia()` y `recargarEnergia(int)` |
| Sobreescritura | `iniciar()` y `toString()` en las dos misiones; `jugar()`; `compareTo()`; `toString()` en `Epoca`, `Evento`, `Consecuencia` |
| Modificadores de acceso | `private` (atributos), `public` (API), `protected` (`Mision.titulo/dificultad/descripcion`, `mostrarEncabezado`, `crearConsecuencia`), por defecto (`Epoca.tieneMisiones()`, clase `RecargaAutomatica`) |
| Clase abstracta | `Mision` (método abstracto `jugar`) |
| Interfaces con su contrato | `Jugabilidad` (`jugar`) y `EntradaUsuario` (`elegir`, `pedirNumero`), implementada por `EntradaConsola` y `EntradaSwing` |
| Polimorfismo | `AgenciaTemporal.jugarMision` llama a `mision.jugar(entrada)` sin saber el tipo de misión ni de entrada |
| `super()` y `this()` | `this(...)` en `Mision`; `super(...)` en las subclases; `super.iniciar()` en las misiones |
| `static` | `MaquinaTemporal.ENERGIA_MAXIMA/COSTO_VIAJE/COSTO_REGRESO`; `Evento.totalEventos` y `Evento.getTotalEventos()`; `RecargaAutomatica.iniciar(...)` |
| GRASP y SOLID | Sección 5 |
| Enumerados | `TipoEvento` (usado por `Evento`) |
| Clases inmutables | `Evento` y `Consecuencia` (clase `final`, atributos `final`, sin setters) |
| Clases internas | `VentanaPrincipal.ConsolaOutputStream` |

### Unidad 4 — Colecciones
| Tema | Dónde |
|---|---|
| Colecciones genéricas | `List<Epoca>`, `List<Mision>`, `List<Consecuencia>`, `List<Evento>`; `Map<String, Integer>` (`TreeMap`) en `Jugador` |
| Orden natural (`Comparable`) | `Mision implements Comparable<Mision>` (por dificultad); se usa en `Epoca.getMisiones()` con `Collections.sort` |
| Orden personalizado (`Comparator`) | `ComparadorMisionPorTitulo`, usado en `Jugador.mostrarHistorial()` |
| Recorrido con cursores | `for-each` en `Epoca.mostrarMisiones`, `LineaTemporal.mostrarFuturo`, `Jugador.mostrarHistorial` (incluye `entrySet()` del mapa) |

### Unidad 5 — Excepciones
| Tema | Dónde |
|---|---|
| `try` / `catch` / `finally` | `AgenciaTemporal.jugarMision`: captura `InputMismatchException` y el `finally` siempre intenta regresar al presente; también en `Main`, `EntradaConsola.leerEntero` y `EntradaSwing.pedirNumero` (`NumberFormatException`) |
| `throw` y `throws` | `MaquinaTemporal.viajar` y `regresarAlPresente` (`throws EnergiaInsuficienteException`); `Epoca.getMision` (`throws MisionInvalidaException`) |
| Excepciones propias | `EnergiaInsuficienteException` y `MisionInvalidaException` (ambas extienden `Exception`) |

### Unidad 6 — Swing e hilos
| Tema | Dónde |
|---|---|
| Interfaz gráfica con eventos | `VentanaPrincipal` (botones y combos con `ActionListener`), `EntradaSwing` (cuadros de diálogo), `MainGUI` |
| Hilos de ejecución | `RecargaAutomatica implements Runnable`: hilo `recarga-energia` que suma +5 de energía cada 5 s mientras se juega. `MaquinaTemporal` usa `synchronized` porque el hilo y el jugador comparten la energía; la GUI se actualiza con `SwingUtilities.invokeLater` |
| Archivos | No se usa: se eligió la alternativa de interfaz gráfica (a confirmar con el docente) |

## 5. Patrones GRASP y principios SOLID aplicados

| Tipo | Principio | Dónde | Por qué |
|---|---|---|---|
| GRASP | Controller | `AgenciaTemporal` | Recibe las operaciones del usuario (`jugarMision`, `regresarAlPresente`) y coordina `Jugador`, `Epoca`, `MaquinaTemporal` y `LineaTemporal` |
| GRASP | Information Expert | `Mision.aumentarDificultad`, `LineaTemporal.aplicarCambios` | Cada una tiene los datos que necesita (dificultad; consecuencias y estabilidad) |
| GRASP | Creator | `AgenciaTemporal` crea su `LineaTemporal`; `Mision.crearConsecuencia` crea las `Consecuencia` | Quien contiene o usa estrechamente a los objetos es quien los crea |
| GRASP | Polimorfismo | `Mision.jugar` en `MisionDetective` / `MisionPlataformas` | Cada tipo de misión implementa su propio minijuego |
| GRASP | Bajo acoplamiento | `Mision` depende de `EntradaUsuario`, no de la consola ni de Swing | Se puede cambiar la interfaz sin tocar las misiones |
| SOLID | Responsabilidad única | `Jugador` guarda el estado del jugador; `Mision` el de la misión; `LineaTemporal` los efectos | Cada clase tiene una sola razón para cambiar |
| SOLID | Abierto/cerrado | `Mision` y `EntradaUsuario` | Se agregan misiones o formas de entrada nuevas sin modificar `AgenciaTemporal` |
| SOLID | Sustitución de Liskov | `MisionDetective` / `MisionPlataformas` | Cualquiera reemplaza a `Mision` en `jugarMision` sin romper nada |
| SOLID | Inversión de dependencias | `AgenciaTemporal` → `Mision`; `Mision` → `EntradaUsuario` | Se depende de abstracciones (clase abstracta e interfaz), no de clases concretas |
