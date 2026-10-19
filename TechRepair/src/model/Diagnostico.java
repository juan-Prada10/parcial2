package model;

public class Diagnostico extends Servicio {
    public Diagnostico(String codigo, String nombre, String descripcion, double tarifaBase, Detalle detalle, TechRepair ownedByTechRepair) {
        super(codigo, nombre, descripcion, 35000.0,detalle,ownedByTechRepair);
    }

    @Override
    public double calcularTotalServicio(byte horasTrabajo) {
        return tarifaBase;
    }
}
