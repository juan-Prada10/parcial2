package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TechRepair {

    private String direccion;
    private final String nit;

    private List<InventarioRepuesto> listaInventarioRepuesto = new ArrayList<>();
    private List<OrdenServicio> listaOrdenServicio = new ArrayList<>();
    private List<Cliente> listaCliente = new ArrayList<>();
    private List<Tecnico> listaTecnicos = new ArrayList<>();

    public TechRepair(String nit, String direccion) {
        this.nit = nit;
        this.direccion = direccion;
    }

    public String getNit() {
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
                .anyMatch(client -> client.getDocumento().equals(cliente.getDocumento()));
        if (!existe) {
            listaCliente.add(cliente);
            return true;
        }
        System.out.println("Ya existe un cliente con ese documento.");
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
    //2. REGISTRAR EQUIPO
    public boolean registrarEquipo(String documentoCliente, EquipoTecnologico equipo) {
        boolean codigoRepetido = listaCliente.stream()
                .flatMap(cliente -> cliente.getListaEquipoTecnologico().stream())
                .anyMatch(equipoTecnologico -> equipoTecnologico.getCodigo().equals(equipo.getCodigo()));
        if (codigoRepetido) {
            System.out.println("Ya existe un equipo registrado con ese código.");
            return false;
        }
        Optional<Cliente> clienteOpt = buscarClientePorDocumento(documentoCliente);
        if (clienteOpt.isPresent()) {
            clienteOpt.get().getListaEquipoTecnologico().add(equipo);
            return true;
        }
        return false;
    }

    //3. REGISTRAR TECNICO
    public boolean registrarTecnico(Tecnico tecnico) {
        boolean existe = listaTecnicos.stream()
                .anyMatch(tecnicoExistente -> tecnicoExistente.getCodigo().equals(tecnico.getCodigo()) || tecnicoExistente.getDocumento().equals(tecnico.getDocumento()));
        if (!existe) {
            listaTecnicos.add(tecnico);
            return true;
        }
        return false;
    }
    public Optional<Tecnico> buscarTecnicoPorCodigo(String codigo) {
        return listaTecnicos.stream()
                .filter(tecnico -> tecnico.getCodigo().equals(codigo))
                .findFirst();
    }

    public boolean actualizarTecnico(String codigo, String nuevoNombre, Especialidad nuevaEspecialidad, EstadoLaboral nuevoEstado) {
        Optional<Tecnico> tecnicoOpt = buscarTecnicoPorCodigo(codigo);
        if (tecnicoOpt.isPresent()) {
            Tecnico tecnico = tecnicoOpt.get();
            tecnico.setNombre(nuevoNombre);
            tecnico.setEspecialidad(nuevaEspecialidad);
            tecnico.setEstadoLaboral(nuevoEstado);
            return true;
        }
        return false;
    }

    public boolean eliminarTecnico(String codigo) {
        return listaTecnicos.removeIf(tecnico -> tecnico.getCodigo().equals(codigo));
    }

    //4. REGISTRAR REPUESTO
    public boolean registrarRepuesto(InventarioRepuesto repuesto) {
        boolean existe = listaInventarioRepuesto.stream()
                .anyMatch(r -> repuesto.getCodigo().equals(repuesto.getCodigo()));
        if (!existe) {
            listaInventarioRepuesto.add(repuesto);
            return true;
        }
        return false;
    }

    public Optional<InventarioRepuesto> buscarRepuestoPorCodigo(String codigo) {
        return listaInventarioRepuesto.stream()
                .filter(r -> r.getCodigo().equals(codigo))
                .findFirst();
    }

    public boolean actualizarRepuesto(String codigo, String nuevoNombre, double nuevoPrecio, int nuevaCantidad) {
        Optional<InventarioRepuesto> repOpt = buscarRepuestoPorCodigo(codigo);
        if (repOpt.isPresent()) {
            InventarioRepuesto r = repOpt.get();
            r.setNombre(nuevoNombre);
            r.setPrecioUnitario(nuevoPrecio);
            r.setCantidadDisponible(nuevaCantidad);
            return true;
        }
        return false;
    }

    public boolean eliminarRepuesto(String codigo) {
        return listaInventarioRepuesto.removeIf(r -> r.getCodigo().equals(codigo));
    }

    //5. CREAR ORDEN SERVICIO
    public OrdenServicio crearOrdenServicio(String codigo, String documentoCliente, String observaciones) {
        boolean existe = listaOrdenServicio.stream()
                .anyMatch(o -> o.codigo().equals(codigo));
        if (existe) {
            System.out.println("Ya existe una orden con el código " + codigo);
            return null;
        }
        Optional<Cliente> clienteOpt = buscarClientePorDocumento(documentoCliente);
        if (clienteOpt.isPresent()) {
            OrdenServicio orden = new OrdenServicio(codigo, LocalDate.now(), observaciones, Estado.ABIERTA, this, clienteOpt.get(), new ArrayList<>()
            );
            listaOrdenServicio.add(orden);
            return orden;
        }
        return null;
    }

}


