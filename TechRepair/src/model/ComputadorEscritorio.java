package model;

import java.time.LocalDate;

public class ComputadorEscritorio extends EquipoTecnologico{

    private String tipoProcesador;
    private byte capacidadMemoriaRAM;
    private boolean tarjetaGrafica;


    public ComputadorEscritorio(String codigo, Cliente ownedByCliente, int almacenamiento, String estadoFisico, LocalDate añoFabricacion, String marca, String modelo, String serie, String tipoProcesador, byte capacidadMemoriaRAM, boolean tarjetaGrafica) {
        super(codigo, ownedByCliente, almacenamiento, estadoFisico, añoFabricacion, marca, modelo, serie);
        this.tipoProcesador=tipoProcesador;
        this.capacidadMemoriaRAM=capacidadMemoriaRAM;
        this.tarjetaGrafica=tarjetaGrafica;
    }

    public String getTipoProcesador() {
        return tipoProcesador;
    }

    public void setTipoProcesador(String tipoProcesador) {
        this.tipoProcesador = tipoProcesador;
    }

    public boolean isTarjetaGrafica() {
        return tarjetaGrafica;
    }

    public void setTarjetaGrafica(boolean tarjetaGrafica) {
        this.tarjetaGrafica = tarjetaGrafica;
    }

    public byte getCapacidadMemoriaRAM() {
        return capacidadMemoriaRAM;
    }

    public void setCapacidadMemoriaRAM(byte capacidadMemoriaRAM) {
        this.capacidadMemoriaRAM = capacidadMemoriaRAM;
    }
}
