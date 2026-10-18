package model;

public class InventarioRepuesto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precioUnitario;
    private int cantidadDisponible;
    private Compatibilidad categoriaCompatibilidad;
    private TechRepair ownedByTechRepair;

    public InventarioRepuesto(String codigo, String nombre, String descripcion, double precioUnitario, int cantidadDisponible, Compatibilidad categoriaCompatibilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidadDisponible = cantidadDisponible;
        this.categoriaCompatibilidad = categoriaCompatibilidad;
        this.ownedByTechRepair = ownedByTechRepair;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public Compatibilidad getCategoriaCompatibilidad() {
        return categoriaCompatibilidad;
    }

    public void setCategoriaCompatibilidad(Compatibilidad categoriaCompatibilidad) {
        this.categoriaCompatibilidad = categoriaCompatibilidad;
    }

    public TechRepair getOwnedByTechRepair() {
        return ownedByTechRepair;
    }

    public void setOwnedByTechRepair(TechRepair ownedByTechRepair) {
        this.ownedByTechRepair = ownedByTechRepair;
    }

    public boolean descontarRepuesto(int cantidad) {
        if (cantidad > 0 && cantidadDisponible >= cantidad) {
            this.cantidadDisponible -= cantidad;
            return true;
        }
        return false;
    }
}


