package components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// -------------------
// Clase Agenda
// Esta clase es como un "contenedor" de tareas. 
// Permite agregar, eliminar, editar y obtener todas las tareas
// -------------------
public class SecionPanel {
    private List<module.BotonTarea> tareas;  // Creamos una lista para almacenar las tareas

    private JPanel panel;

    // Constructor: inicializa la lista vacía
    public SecionPanel() {
        tareas = new ArrayList<>();
    }

    // Método para agregar una tarea a la lista
    public void agregarTarea(module.BotonTarea t) { 
        tareas.add(t); 
    }

    // Método para eliminar una tarea de la lista usando su índice
    public void eliminarTarea(int index) { 
        tareas.remove(index); 
    }

    // Método para editar una tarea existente
    public void editarTarea(int index, module.BotonTarea t) { 
        tareas.set(index, t); 
    }

    // Método para obtener la lista completa de tareas
    public List<module.BotonTarea> getTareas() { 
        return tareas; 
    }

    // Método para obtener los títulos de todas las tareas (para selección)
    public String[] getTitulosTareas() {
        String[] titulos = new String[tareas.size()];
        for (int i = 0; i < tareas.size(); i++) {
            titulos[i] = tareas.get(i).getTitulo();
        }
        return titulos;
    }

    // Método para obtener una tarea por índice
    public module.BotonTarea getTarea(int index) {
        if (index >= 0 && index < tareas.size()) {
            return tareas.get(index);
        }
        return null;
    }
}