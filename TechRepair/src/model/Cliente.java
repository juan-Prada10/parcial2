package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cliente {

    private final String documento;
    private String nombreCompleto;
    private String numeroTelefonico;
    private String correo;
    private String direccion;

    private TechRepair ownedByTechRepair;
    private OrdenServicio ordenServicio;
    private List<EquipoTecnologico> listaEquipoTecnologico = new ArrayList<>();

    public Cliente(String documento, TechRepair ownedByTechRepair, OrdenServicio ordenServicio, String direccion, String correo, String numeroTelefonico, String nombreCompleto) {
        this.documento = documento;
        this.ownedByTechRepair = ownedByTechRepair;
        this.ordenServicio = ordenServicio;
        this.direccion = direccion;
        this.correo = correo;
        this.numeroTelefonico = numeroTelefonico;
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumento(){
        return documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TechRepair getOwnedByTechRepair() {
        return ownedByTechRepair;
    }

    public void setOwnedByTechRepair(TechRepair ownedByTechRepair) {
        this.ownedByTechRepair = ownedByTechRepair;
    }

    public OrdenServicio getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public List<EquipoTecnologico> getListaEquipoTecnologico() {
        return listaEquipoTecnologico;
    }

    public void setListaEquipoTecnologico(List<EquipoTecnologico> listaEquipoTecnologico) {
        this.listaEquipoTecnologico = listaEquipoTecnologico;
    }

    //CRUD

    public Optional<EquipoTecnologico> buscarEquipo(String codigo){
        return listaEquipoTecnologico.stream().filter(equipoTecnologico -> equipoTecnologico.getCodigo().equalsIgnoreCase(codigo)).findFirst();
    }

    public String crearPortatil(Portatil portatil){
        Optional<EquipoTecnologico> equipoEncontrado = buscarEquipo(portatil.getCodigo());
        if (equipoEncontrado.isEmpty()){
            listaEquipoTecnologico.add(portatil);
            return "El portatil se registro con exito";
        }else{return "El portatil ya esta registrado";}
    }

    public String crearComputadorEscritorio(ComputadorEscritorio computadorEscritorio){
        Optional<EquipoTecnologico> equipoEncontrado = buscarEquipo(computadorEscritorio.getCodigo());
        if (equipoEncontrado.isEmpty()){
            listaEquipoTecnologico.add(computadorEscritorio);
            return "El computador de escritorio se registro con exito";
        }else{return "El computador de escritorio ya esta registrado";}
    }

    public String crearTelefonoInteligente(TelefonoInteligente telefonoInteligente){
        Optional<EquipoTecnologico> equipoEncontrado = buscarEquipo(telefonoInteligente.getCodigo());
        if (equipoEncontrado.isEmpty()){
            listaEquipoTecnologico.add(telefonoInteligente);
            return "El portatil se registro con exito";
        }else{return "El portatil ya esta registrado";}
    }

    public String eliminarEquipo(String codigo){
        Optional<EquipoTecnologico> equipoEncontrado = buscarEquipo(codigo);
        if (equipoEncontrado.isPresent()){
            listaEquipoTecnologico.remove(equipoEncontrado.get());
            return "Equipo eliminado con exito";
        }else{return "El equipo no existe";}
    }

}
