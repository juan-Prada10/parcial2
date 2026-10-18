package model;

public class Diagnostico extends Servicio {
    public Diagnostico(String codigo, String nombre, String descripcion) {
        super(codigo, nombre, descripcion, 35000.0, );
    }

    @Override
    public double calcularTotalServicio(byte horasTrabajo) {
        return tarifaBase;
    }
}
