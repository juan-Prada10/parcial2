package model;

public class Tecnico {

    private final String codigo;
    private String nombre;
    private String documento;
    private Especialidad especialidad;
    private EstadoLaboral estadoLaboral;

    private TechRepair ownedByTechRepair;
    private Detalle detalle;

    public Tecnico(String codigo, Detalle detalle, TechRepair ownedByTechRepair, EstadoLaboral estadoLaboral, Especialidad especialidad, String documento, String nombre) {
        this.codigo = codigo;
        this.detalle = detalle;
        this.ownedByTechRepair = ownedByTechRepair;
        this.estadoLaboral = estadoLaboral;
        this.especialidad = especialidad;
        this.documento = documento;
        this.nombre = nombre;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public EstadoLaboral getEstadoLaboral() {
        return estadoLaboral;
    }

    public void setEstadoLaboral(EstadoLaboral estadoLaboral) {
        this.estadoLaboral = estadoLaboral;
    }

    public TechRepair getOwnedByTechRepair() {
        return ownedByTechRepair;
    }

    public void setOwnedByTechRepair(TechRepair ownedByTechRepair) {
        this.ownedByTechRepair = ownedByTechRepair;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }
}
