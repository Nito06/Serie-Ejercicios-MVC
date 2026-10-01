package vista;

import java.util.List;
import java.util.Scanner;
import modelo.Producto;

public class VistaConsola {
    private Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n1) Agregar 2) Vender 3) Listar 4) Valor total 0) Salir");
        System.out.print("Opcion: ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String pedirString(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public int pedirInt(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public double pedirDouble(String mensaje) {
        System.out.print(mensaje);
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void listarProductos(List<Producto> productos) {
        for (Producto p : productos) {
            System.out.printf("%-15s %4d $%.2f%n", p.getNombre(), p.getCantidad(), p.getPrecio());
        }
    }

    public void mostrarValorTotal(double total) {
        System.out.println("Valor del inventario: $" + total);
    }
}