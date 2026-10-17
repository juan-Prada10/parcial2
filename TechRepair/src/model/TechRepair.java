package model;

import java.util.ArrayList;
import java.util.List

public class TechRepair {

    private String direccion;
    private final String nit;

    private List<InventarioRepuesto> listaInventarioRepuesto= new ArrayList<>();
    private List<OrdenServicio> listaOrdenServicio = new ArrayList<>();
    private List<Cliente> listaCliente = new ArrayList<>();
    private List<Tecnico> listaTecnicos = new ArrayList<>();

    public TechRepair(String nit, String direccion) {
        this.nit = nit;
        this.direccion = direccion;
    }

    public String getNit(){
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<InventarioRepuesto> getListaInventarioRepuesto() {
        return listaInventarioRepuesto;
    }

    public void setListaInventarioRepuesto(List<InventarioRepuesto> listaInventarioRepuesto) {
        this.listaInventarioRepuesto = listaInventarioRepuesto;
    }

    public List<OrdenServicio> getListaOrdenServicio() {
        return listaOrdenServicio;
    }

    public void setListaOrdenServicio(List<OrdenServicio> listaOrdenServicio) {
        this.listaOrdenServicio = listaOrdenServicio;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<Tecnico> getListaTecnicos() {
        return listaTecnicos;
    }

    public void setListaTecnicos(List<Tecnico> listaTecnicos) {
        this.listaTecnicos = listaTecnicos;
    }
}
