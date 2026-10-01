package modelo;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos = new ArrayList<>();

public void agregar(String nombre, int cantidad, double precio) {
        if (nombre == null || nombre.trim().isEmpty() || cantidad < 0 || precio <= 0) {
            throw new IllegalArgumentException("Datos inválidos");
        }

        Producto existente = buscar(nombre);
        
        if (existente != null) {
            // Suma el inventario si el producto ya existe y valida el límite
            if (existente.getCantidad() + cantidad > 500) {
                throw new IllegalArgumentException("El stock total no puede superar las 500 unidades");
            }
            existente.setCantidad(existente.getCantidad() + cantidad);
        } else {
            // Verifica el límite al ingresar un producto nuevo
            if (cantidad > 500) {
                throw new IllegalArgumentException("Un producto no puede tener más de 500 unidades en almacén");
            }
            productos.add(new Producto(nombre, cantidad, precio));
        }
    }

    public Producto buscar(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void vender(String nombre, int cantidad) {
        Producto p = buscar(nombre);
        if (p == null) {
            throw new IllegalArgumentException("El producto no existe");
        }
        if (cantidad > p.getCantidad()) {
            throw new StockInsuficienteException("Stock insuficiente");
        }
        p.setCantidad(p.getCantidad() - cantidad);
    }

    public List<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getCantidad() * p.getPrecio();
        }
        return total;
    }
}