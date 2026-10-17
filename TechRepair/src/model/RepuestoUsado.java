package model;

public class RepuestoUsado {
    private String codigo;
    private byte cantidad;
    private InventarioRepuesto inventarioRepuesto;

    public RepuestoUsado(String codigo, byte cantidad, InventarioRepuesto inventarioRepuesto) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.inventarioRepuesto = inventarioRepuesto;
    }

    public double calcularCostoTotal() {
        return cantidad * inventarioRepuesto.getPrecioUnitario();
    }

    public byte getCantidad() { return cantidad; }
    public InventarioRepuesto getInventarioRepuesto() { return inventarioRepuesto; }
}
