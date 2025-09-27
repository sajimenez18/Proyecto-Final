import javax.swing.SwingUtilities;
import views.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Agenda Tareas");

        SwingUtilities.invokeLater(() -> {
            new LoginView();
        });
    }
}
