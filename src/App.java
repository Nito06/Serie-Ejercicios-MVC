import controlador.ControladorInventario;
import modelo.Inventario;
import vista.VistaConsola;

public class App {
    public static void main(String[] args) {
        Inventario modelo = new Inventario();
        VistaConsola vista = new VistaConsola();
        
        ControladorInventario controlador = new ControladorInventario(modelo, vista);
        controlador.iniciar();
    }
}