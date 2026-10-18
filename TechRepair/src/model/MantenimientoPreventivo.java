package model;

public class MantenimientoPreventivo extends Servicio {
    private TipoEquipo tipoEquipo;

    public MantenimientoPreventivo(String codigo, String nombre, String descripcion, TipoEquipo tipoEquipo) {
        super(codigo, nombre, descripcion, obtenerTarifaPorTipo(tipoEquipo), null);
        this.tipoEquipo = tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipo tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    private static double obtenerTarifaPorTipo(TipoEquipo tipo) {
        if (tipo == TipoEquipo.COMPUTADORES_PORTATILES) {
            return 60000.0;
        } else if (tipo == TipoEquipo.COMPUTADORES_ESCRITORIO) {
            return 70000.0;
        } else if (tipo == TipoEquipo.TELEFONOS_INTELIGENTES) {
            return 40000.0;
        } else {
            return 0.0;
        }
    }

    @Override
    public double calcularTotalServicio(byte horasTrabajo) {
        return tarifaBase;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }
}


