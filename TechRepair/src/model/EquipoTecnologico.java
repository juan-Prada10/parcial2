package model;

import java.time.LocalDate;

public abstract class EquipoTecnologico {

    private final String codigo;
    private String serie;
    private String marca;
    private String modelo;
    private LocalDate añoFabricacion;
    private String estadoFisico;
    private int almacenamiento;

    private Cliente ownedByCliente;

    public EquipoTecnologico(String codigo, Cliente ownedByCliente, int almacenamiento, String estadoFisico, LocalDate añoFabricacion, String marca, String modelo, String serie) {
        this.codigo = codigo;
        this.ownedByCliente = ownedByCliente;
        this.almacenamiento = almacenamiento;
        this.estadoFisico = estadoFisico;
        this.añoFabricacion = añoFabricacion;
        this.marca = marca;
        this.modelo = modelo;
        this.serie = serie;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getAñoFabricacion() {
        return añoFabricacion;
    }

    public void setAñoFabricacion(LocalDate añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }

    public String getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(int almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public Cliente getOwnedByCliente() {
        return ownedByCliente;
    }

    public void setOwnedByCliente(Cliente ownedByCliente) {
        this.ownedByCliente = ownedByCliente;
    }
}
