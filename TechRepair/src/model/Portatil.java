package model;

import java.time.LocalDate;

public class Portatil extends EquipoTecnologico {

    private byte tamañoPantalla;
    private byte capacidadMemoriaRAM;
    private boolean bateria;


    public Portatil(String codigo, Cliente ownedByCliente, int almacenamiento, String estadoFisico, LocalDate añoFabricacion, String marca, String modelo, String serie, byte tamañoPantalla, byte capacidadMemoriaRAM, boolean bateria) {
        super(codigo, ownedByCliente, almacenamiento, estadoFisico, añoFabricacion, marca, modelo, serie);
        this.tamañoPantalla= tamañoPantalla;
        this.capacidadMemoriaRAM=capacidadMemoriaRAM;
        this.bateria=bateria;
    }

    public byte getTamañoPantalla() {
        return tamañoPantalla;
    }

    public void setTamañoPantalla(byte tamañoPantalla) {
        this.tamañoPantalla = tamañoPantalla;
    }

    public byte getCapacidadMemoriaRAM() {
        return capacidadMemoriaRAM;
    }

    public void setCapacidadMemoriaRAM(byte capacidadMemoriaRAM) {
        this.capacidadMemoriaRAM = capacidadMemoriaRAM;
    }

    public boolean isBateria() {
        return bateria;
    }

    public void setBateria(boolean bateria) {
        this.bateria = bateria;
    }
}
