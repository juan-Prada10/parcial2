package model;

public class RepuestoUsado {
    private String codigo;
    private byte cantidad;
    private InventarioRepuesto inventarioRepuesto;

    private ReparacionCorrectiva ownedByReparacionCorrectiva;

    public RepuestoUsado(String codigo, byte cantidad, InventarioRepuesto inventarioRepuesto,  ReparacionCorrectiva ownedByReparacionCorrectiva) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.inventarioRepuesto = inventarioRepuesto;
        this.ownedByReparacionCorrectiva = ownedByReparacionCorrectiva;
    }

    public ReparacionCorrectiva getOwnedByreparacionCorrectiva() {
        return ownedByReparacionCorrectiva;
    }

    public void setOwnedByreparacionCorrectiva(ReparacionCorrectiva ownedByreparacionCorrectiva) {
        this.ownedByReparacionCorrectiva = ownedByreparacionCorrectiva;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCantidad(byte cantidad) {
        this.cantidad = cantidad;
    }

    public void setInventarioRepuesto(InventarioRepuesto inventarioRepuesto) {
        this.inventarioRepuesto = inventarioRepuesto;
    }

    public double calcularCostoTotal() {
        return cantidad * inventarioRepuesto.getPrecioUnitario();
    }

    public byte getCantidad() { return cantidad; }
    public InventarioRepuesto getInventarioRepuesto() { return inventarioRepuesto; }
}
