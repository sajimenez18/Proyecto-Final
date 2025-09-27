package database;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class HistorialDataBase {
    private static final String RUTA_HISTORIAL_TAREAS = "database/historialTareas.txt";
    private static final String RUTA_HISTORIAL_ALARMAS = "database/historialAlarmas.txt";

    // Crear carpeta database si no existe
    static {
        File carpeta = new File("database");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }

    // Método para guardar acciones de tareas
    public static void guardarAccionTarea(String accion, String titulo, String prioridad) {
        guardarEnArchivo(RUTA_HISTORIAL_TAREAS, accion, titulo, prioridad, "");
    }

    // Método para guardar ediciones de tareas (con información del antes)
    public static void guardarEdicionTarea(String tituloAnterior, String tituloNuevo, String prioridad) {
        String accion = "EDITADA (antes: " + tituloAnterior + ")";
        guardarEnArchivo(RUTA_HISTORIAL_TAREAS, accion, tituloNuevo, prioridad, "");
    }

    // Método para guardar acciones de alarmas
    public static void guardarAccionAlarma(String descripcion, String estado) {
        guardarEnArchivo(RUTA_HISTORIAL_ALARMAS, estado, descripcion, "", "");
    }

    // Método genérico para guardar en archivo
    private static void guardarEnArchivo(String ruta, String accion, String elemento, String extra1, String extra2) {
        try (FileWriter fw = new FileWriter(ruta, true)) {
            String linea = "[" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "] "
                         + accion + " - " + elemento;
            
            if (!extra1.isEmpty()) {
                linea += " (" + extra1 + ")";
            }
            if (!extra2.isEmpty()) {
                linea += " - " + extra2;
            }
            
            fw.write(linea + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para leer historial de tareas
    public static String leerHistorialTareas() {
        return leerArchivo(RUTA_HISTORIAL_TAREAS, "TAREAS");
    }

    // Método para leer historial de alarmas
    public static String leerHistorialAlarmas() {
        return leerArchivo(RUTA_HISTORIAL_ALARMAS, "ALARMAS");
    }

    // Método genérico para leer archivos
    private static String leerArchivo(String ruta, String tipo) {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            StringBuilder sb = new StringBuilder();
            sb.append("=== HISTORIAL DE ").append(tipo).append(" ===\n\n");
            
            int contador = 0;
            while ((linea = br.readLine()) != null) {
                sb.append(++contador).append(". ").append(linea).append("\n");
            }
            
            if (contador == 0) {
                sb.append("No hay historial registrado.");
            }
            
            return sb.toString();
        } catch (Exception e) {
            return "No hay historial de " + tipo.toLowerCase() + " guardado todavía";
        }
    }

    // Método para limpiar historial (opcional)
    public static void limpiarHistorialTareas() {
        limpiarArchivo(RUTA_HISTORIAL_TAREAS);
    }

    public static void limpiarHistorialAlarmas() {
        limpiarArchivo(RUTA_HISTORIAL_ALARMAS);
    }

    private static void limpiarArchivo(String ruta) {
        try (FileWriter fw = new FileWriter(ruta, false)) {
            fw.write(""); // Sobrescribir con contenido vacío
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}