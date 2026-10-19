package model;

public abstract class Servicio {

    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected double tarifaBase;
    private Detalle detalle;
    private TechRepair ownedByTechRepair;

    public Servicio(String codigo, String nombre, String descripcion, double tarifaBase, Detalle detalle, TechRepair ownedByTechRepair) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tarifaBase = tarifaBase;
        this.detalle=detalle;
        this.ownedByTechRepair=ownedByTechRepair;
    }

    public abstract double calcularTotalServicio(byte horasTrabajo);

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }
}
