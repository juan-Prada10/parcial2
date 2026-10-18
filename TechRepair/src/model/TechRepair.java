package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //1. REGISTRO CLIENTES
    public boolean registrarCliente(Cliente cliente) {
        boolean existe = listaCliente.stream()
                .anyMatch(clienteExistente -> clienteExistente.getDocumento().equals(cliente.getDocumento()));
        if (!existe) {
            listaCliente.add(cliente);
            return true;
        }
        return false;
    }

    public Optional<Cliente> buscarClientePorDocumento(String documento) {
        return listaCliente.stream()
                .filter(cliente -> cliente.getDocumento().equals(documento))
                .findFirst();
    }

    public boolean actualizarCliente(String documento, String nuevoNombre, String nuevoTelefono, String nuevoCorreo, String nuevaDireccion) {
        Optional<Cliente> clienteOpt = buscarClientePorDocumento(documento);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setNombreCompleto(nuevoNombre);
            cliente.setNumeroTelefonico(nuevoTelefono);
            cliente.setCorreo(nuevoCorreo);
            cliente.setDireccion(nuevaDireccion);
            return true;
        }
        return false;
    }

    public boolean eliminarCliente(String documento) {
        return listaCliente.removeIf(cliente -> cliente.getDocumento().equals(documento));
    }

}
