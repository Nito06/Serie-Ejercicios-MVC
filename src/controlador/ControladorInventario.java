package controlador;

import modelo.Inventario;
import modelo.Producto;
import modelo.StockInsuficienteException;
import vista.VistaConsola;

public class ControladorInventario {
    private Inventario modelo;
    private VistaConsola vista;

    public ControladorInventario(Inventario modelo, VistaConsola vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        int op;
        do {
            op = vista.mostrarMenu();
            procesarOpcion(op);
        } while (op != 0);
    }

    private void procesarOpcion(int op) {
        switch (op) {
            case 1:
                String nombre = vista.pedirString("Nombre: ");
                int cant = vista.pedirInt("Cantidad: ");
                double precio = vista.pedirDouble("Precio: ");
                try {
                    modelo.agregar(nombre, cant, precio);
                    vista.mostrarMensaje("Agregado.");
                } catch (IllegalArgumentException e) {
                    vista.mostrarMensaje("Datos invalidos");
                }
                break;
            case 2:
                String nombreVenta = vista.pedirString("Producto: ");
                Producto p = modelo.buscar(nombreVenta);
                
                if (p == null) {
                    vista.mostrarMensaje("No existe");
                } else {
                    int cantVenta = vista.pedirInt("Cantidad a vender: ");
                    try {
                        modelo.vender(nombreVenta, cantVenta);
                        vista.mostrarMensaje("Venta ok. Quedan " + p.getCantidad());
                    } catch (StockInsuficienteException e) {
                        vista.mostrarMensaje("Stock insuficiente");
                    } catch (IllegalArgumentException e) {
                        vista.mostrarMensaje(e.getMessage());
                    }
                }
                break;
            case 3:
                vista.listarProductos(modelo.getProductos());
                break;
            case 4:
                vista.mostrarValorTotal(modelo.calcularValorTotal());
                break;
            case 0:
                break;
            default:
                break;
        }
    }
}