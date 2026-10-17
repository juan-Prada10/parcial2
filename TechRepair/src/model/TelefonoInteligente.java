package model;

import java.time.LocalDate;

public class TelefonoInteligente extends EquipoTecnologico{

    private String sistemaOperativo;
    private int capacidadBateria;
    private boolean compatibel5G;


    public TelefonoInteligente(String codigo, Cliente ownedByCliente, int almacenamiento, String estadoFisico, LocalDate añoFabricacion, String marca, String modelo, String serie, String sistemaOperativo, int capacidadBateria, boolean compatibel5G) {
        super(codigo, ownedByCliente, almacenamiento, estadoFisico, añoFabricacion, marca, modelo, serie);
        this.sistemaOperativo= sistemaOperativo;
        this.capacidadBateria=capacidadBateria;
        this.compatibel5G=compatibel5G;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public boolean isCompatibel5G() {
        return compatibel5G;
    }

    public void setCompatibel5G(boolean compatibel5G) {
        this.compatibel5G = compatibel5G;
    }

    public int getCapacidadBateria() {
        return capacidadBateria;
    }

    public void setCapacidadBateria(int capacidadBateria) {
        this.capacidadBateria = capacidadBateria;
    }
}
