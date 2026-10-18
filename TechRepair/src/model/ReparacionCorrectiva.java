package model;

import java.util.ArrayList;
import java.util.List;

public class ReparacionCorrectiva extends Servicio {
    private List<RepuestoUsado> listaRepuestosUsados;

    public ReparacionCorrectiva(String codigo, String nombre, String descripcion) {
        super(codigo, nombre, descripcion, 45000.0, null);
        this.listaRepuestosUsados = new ArrayList<>();
    }

    public void registrarRepuestoUsado(RepuestoUsado repuesto) {
        this.listaRepuestosUsados.add(repuesto);
    }

    @Override
    public double calcularTotalServicio(byte horasTrabajo) {
        double costoManoObra = tarifaBase * horasTrabajo;
        double costoRepuestos = listaRepuestosUsados.stream()
                .mapToDouble(RepuestoUsado::calcularCostoTotal)
                .sum();
        return costoManoObra + costoRepuestos;
    }

    public List<RepuestoUsado> getListaRepuestosUsados() { return listaRepuestosUsados; }
}