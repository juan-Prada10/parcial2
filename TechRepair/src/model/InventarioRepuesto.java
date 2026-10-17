package model;

public class InventarioRepuesto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioUnitario;
    private int cantidadDisponible;
    private Compatibilidad categoriaCompatibilidad;

    public InventarioRepuesto(String codigo, String nombre, String descripcion, double precioUnitario, int cantidadDisponible, Compatibilidad categoriaCompatibilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidadDisponible = cantidadDisponible;
        this.categoriaCompatibilidad = categoriaCompatibilidad;
    }

    public boolean descontarRepuesto(int cantidad) {
        if (cantidad > 0 && cantidadDisponible >= cantidad) {
            this.cantidadDisponible -= cantidad;
            return true;
        }
        return false;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecioUnitario() { return precioUnitario; }
    public int getCantidadDisponible() { return cantidadDisponible; }
}


