package main;

/**
 *
 * @author Jerson Vndré
 */
import view.UsuarioView;
import controller.UsuarioController;

public class Main {

    public static void main(String[] args) {
        UsuarioView view = new UsuarioView();
        UsuarioController controller = new UsuarioController(view);

        view.setVisible(true);
    }
}
