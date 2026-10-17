package model;

import java.util.ArrayList;
import java.util.List;

public class Detalle {

    private final String codigo;
    private String descripcionFalla;
    private byte horasTrabajo;
    private String observaciones;
    private Estado estado;

    private OrdenServicio ownedByOrdenServicio;
    private Tecnico tecnico;
    private EquipoTecnologico equipoTecnologico;
    private List<Servicio> listaServicios= new ArrayList<>();

    public Detalle(String codigo, EquipoTecnologico equipoTecnologico, Tecnico tecnico, OrdenServicio ownedByOrdenServicio, String observaciones, Estado estado, byte horasTrabajo, String descripcionFalla) {
        this.codigo = codigo;
        this.equipoTecnologico = equipoTecnologico;
        this.tecnico = tecnico;
        this.ownedByOrdenServicio = ownedByOrdenServicio;
        this.observaciones = observaciones;
        this.estado = estado;
        this.horasTrabajo = horasTrabajo;
        this.descripcionFalla = descripcionFalla;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public void setDescripcionFalla(String descripcionFalla) {
        this.descripcionFalla = descripcionFalla;
    }

    public byte getHorasTrabajo() {
        return horasTrabajo;
    }

    public void setHorasTrabajo(byte horasTrabajo) {
        this.horasTrabajo = horasTrabajo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public OrdenServicio getOwnedByOrdenServicio() {
        return ownedByOrdenServicio;
    }

    public void setOwnedByOrdenServicio(OrdenServicio ownedByOrdenServicio) {
        this.ownedByOrdenServicio = ownedByOrdenServicio;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public EquipoTecnologico getEquipoTecnologico() {
        return equipoTecnologico;
    }

    public void setEquipoTecnologico(EquipoTecnologico equipoTecnologico) {
        this.equipoTecnologico = equipoTecnologico;
    }
}
