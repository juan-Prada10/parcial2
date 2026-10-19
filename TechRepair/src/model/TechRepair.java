package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TechRepair {

    private String direccion;
    private final String nit;

    private List<InventarioRepuesto> listaInventarioRepuesto = new ArrayList<>();
    private List<OrdenServicio> listaOrdenServicio = new ArrayList<>();
    private List<Cliente> listaCliente = new ArrayList<>();
    private List<Tecnico> listaTecnicos = new ArrayList<>();
    private List<Servicio> listaServicios = new ArrayList<>();

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

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
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
        if (repuesto.getPrecioUnitario() <= 0 || repuesto.getCantidadDisponible() < 0) {
            System.out.println("El precio debe ser mayor a 0 y las existencias no pueden ser negativas.");
            return false;
        }
        boolean existe = listaInventarioRepuesto.stream()
                .anyMatch(r -> r.getCodigo().equals(repuesto.getCodigo()));
        if (!existe) {
            listaInventarioRepuesto.add(repuesto);
            return true;
        }
        System.out.println("Ya existe un repuesto con ese código.");
        return false;
    }
    public Optional<InventarioRepuesto> buscarRepuestoPorCodigo(String codigo) {
        return listaInventarioRepuesto.stream()
                .filter(repuesto -> repuesto.getCodigo().equals(codigo))
                .findFirst();
    }
    public boolean actualizarRepuesto(String codigo, String nuevoNombre, double nuevoPrecio, int nuevaCantidad) {
        if (nuevoPrecio <= 0 || nuevaCantidad < 0) {
            System.out.println("El precio debe ser mayor a 0 y las existencias no pueden ser negativas.");
            return false;
        }
        Optional<InventarioRepuesto> repOpt = buscarRepuestoPorCodigo(codigo);
        if (repOpt.isPresent()) {
            InventarioRepuesto repuesto = repOpt.get();
            repuesto.setNombre(nuevoNombre);
            repuesto.setPrecioUnitario(nuevoPrecio);
            repuesto.setCantidadDisponible(nuevaCantidad);
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
    
    //6. Agregar detalle a una orden

    public List<EquipoTecnologico> equiposCliente(String documento){
        return listaCliente.stream().filter(cliente -> cliente.getDocumento().equalsIgnoreCase(documento)).flatMap
                (cliente -> cliente.getListaEquipoTecnologico().stream()).toList();
    }

    public Optional<Detalle> buscarDetalle(String codigo){
        return listaOrdenServicio.stream().flatMap(ordenServicio -> ordenServicio.listaDetalles().stream().filter(detalle -> detalle.getCodigo().equalsIgnoreCase(codigo))).findFirst();
    }

    public Optional<OrdenServicio> buscarOrdenPorCodigo(String codigo){
        return listaOrdenServicio.stream().filter
                (ordenServicio -> ordenServicio.codigo().equalsIgnoreCase(codigo)).findFirst();
    }

    public String agregarDetalle(String documento, String codigo, Detalle detalle,Servicio servicio){
        List<EquipoTecnologico> equipoDelCliente = equiposCliente(documento);
        Optional<OrdenServicio> ordenEncontrada= buscarOrdenPorCodigo(codigo);
        Optional<Detalle> detalleEncontrado= buscarDetalle(detalle.getCodigo());
        if(ordenEncontrada.isPresent()) {
            if (ordenEncontrada.get().estado() == Estado.ABIERTA) {
                if (detalleEncontrado.isEmpty()){
                    if (equipoDelCliente.stream().anyMatch(equipo -> equipo.getModelo().equalsIgnoreCase(detalle.getEquipoTecnologico().getModelo()))) {
                        ordenEncontrada.get().crearDetalle(detalle);
                        return "Detalle creado con exito";
                    } else {return "Este equipo no es del cliente";}

                }else {return "El detalle ya esta duplicado";}

            }else {return "La orden ya no permite agregar detalles";}

        }else{return "La orden buscada no existe";}

    }

    //7.Asignar técnico

    public String asignarTecnico(Tecnico tecnico, String codigo){
        Optional<Detalle> detalleEncontrado = buscarDetalle(codigo);
        if (tecnico.getEstadoLaboral()==EstadoLaboral.ACTIVO && detalleEncontrado.isPresent()){
            detalleEncontrado.get().setTecnico(tecnico);
            return "Tecnico registrado con exito";
        }else{return "El tecnico no se encuentra en servicio o el detalle no existe";}
    }

    //8. Registrar repuestos utilizados
    //(se encuentra en el main)

    //9.Calcular valor de un detalle
    public String calcularValorDetalle(String codigo){
        Optional<Detalle> detalleEncontrado= buscarDetalle(codigo);
        double total =0;
        if (detalleEncontrado.isPresent()){
            total = detalleEncontrado.get().calcularSubTotalDetalle();
            return "El total del detalle es"+ total;
        }else{return "El detalle no existe";}
    }

    //10.Calcular valor total de la orden
    public String calcularValorOrden(String codigo){
        double total=0;
        Optional<OrdenServicio> ordenServicioEncontrada = buscarOrdenPorCodigo(codigo);
        if (ordenServicioEncontrada.isPresent()){
            total = ordenServicioEncontrada.get().caocularTotalOrden();
            return "El costo de la orden es de: "+ total;
        }else{return "la orden no existe ";}
    }

    //11.Actualizar estado de un detalle

    public String actualizarDetalle(String codigo, Estado estado){
        Optional<Detalle> detalleEncontrado = buscarDetalle(codigo);
        if(detalleEncontrado.isPresent()){
            detalleEncontrado.get().setEstado(estado);
            return "El estado del detalle se actualizo con exito";
        }else{return "El detalle no existe";}
    }

    //12.Finalizar una orden

    public String finalizarOrden(String codigo){
        boolean todasFinalizadas = false;
        Estado estado = Estado.FINALIZADA;
        Optional<OrdenServicio> ordenServicioEncontrada=buscarOrdenPorCodigo(codigo);
        if (ordenServicioEncontrada.isPresent()){
            todasFinalizadas = ordenServicioEncontrada.get().listaDetalles().stream().allMatch(detalle -> detalle.getEstado()==Estado.FINALIZADA);
            if(todasFinalizadas){
                return "Orden finalizada";
            }else {return "La orden no a sido finalizada";}
        }else {return "La orden no existe";}
    }

    //13.Consultar órdenes de un cliente
    public List<OrdenServicio> consultarOrdenesCliente(String documento){
        List<OrdenServicio> listaOrdenesCliente=new ArrayList<>();
        Optional<Cliente> clienteEncontrado= buscarClientePorDocumento(documento);
        if (clienteEncontrado.isPresent()){
             OrdenServicio orden= clienteEncontrado.get().getOrdenServicio();
              listaOrdenesCliente.add(orden);
              return listaOrdenesCliente;
        }
        return listaOrdenesCliente;
    }

    //14.Consultar trabajos de un tecnico
    public List<Detalle> listaTrabajosTecnico(String codigo){
        List<Detalle> listaTrabajosTecnico= new ArrayList<>();
        Optional<Tecnico> tecnicoEncontrado= buscarTecnicoPorCodigo(codigo);
        if (tecnicoEncontrado.isPresent()){
            Detalle detalleTecnico= tecnicoEncontrado.get().getDetalle();
            listaTrabajosTecnico.add(detalleTecnico);
            return listaTrabajosTecnico;
        }
        return listaTrabajosTecnico;
    }

    public void mostrarAgrupadosPorEstado(String codigo) {
        listaTrabajosTecnico(codigo).stream()
                .collect(Collectors.groupingBy(Detalle::getEstado))
                .forEach((estado, detalles) -> {
                    System.out.println("Estado: " + estado);

                    detalles.forEach(detalle ->
                            System.out.println(detalle)
                    );
                });
    }

    //15.Consultar equipos pendientes
    public List<Detalle> equiposPendientes(){
        List<Detalle> equiposPendientes= new ArrayList<>();
        equiposPendientes = listaOrdenServicio.stream().flatMap(ordenServicio -> ordenServicio.listaDetalles().stream().filter(detalle -> detalle.getEstado()==Estado.EN_PROCESO)).toList();
        return equiposPendientes;
    }

    //16
    //(se encuentra en el main)

    //17
    //(se encuentra en el main)
    //18Consultar inventario crítico

    public List<InventarioRepuesto> repuestosCriticos(){
        List<InventarioRepuesto> inventarioRepuestosCriticos = new ArrayList<>();
        inventarioRepuestosCriticos = listaInventarioRepuesto.stream().filter(inventarioRepuesto -> inventarioRepuesto.getCantidadDisponible()<5).toList();
        return inventarioRepuestosCriticos;
    }
    
    public Optional<Servicio> buscarServicio(String codigo){
        return listaServicios.stream().filter(servicio -> servicio.codigo.equalsIgnoreCase(codigo)).findFirst();
    }

    public String crearServicio(Servicio servicio){
        Optional<Servicio> servicioEncontrado = buscarServicio(servicio.codigo);
        if(servicioEncontrado.isEmpty()){
            listaServicios.add(servicio);
            return "El servicio se creo con exito";
        }else{return "El servicio ya existe";}
    }

    public String crearServicioPreventivo(MantenimientoPreventivo mantenimientoPreventivo){
        Optional<Servicio> servicioEncontrado = buscarServicio(mantenimientoPreventivo.codigo);
        if(servicioEncontrado.isEmpty()){
            listaServicios.add(mantenimientoPreventivo);
            return "El servicio se creo con exito";
        }else{return "El servicio ya existe";}
    }

    public String eliminarServicio(String codigo){
        Optional<Servicio> servicioEncontrado= buscarServicio(codigo);
        if(servicioEncontrado.isPresent()){
            listaServicios.remove(servicioEncontrado.get());
            return "Se elimino el servicio con exito";
        }else{return "El servicio no existe";}
    }

    public String actualizarServicio(String codigo, String nuevoNombre, String nuevaDescripcion, double nuevaTarifaBase){
        Optional<Servicio> servicioEncontrado= buscarServicio(codigo);
        if (servicioEncontrado.isPresent()){
            servicioEncontrado.get().setNombre(nuevoNombre);
            servicioEncontrado.get().setDescripcion(nuevaDescripcion);
            servicioEncontrado.get().setTarifaBase(nuevaTarifaBase);

            return "Se actualizo con exito el servicio";
        }else{return "El servicio no fue encontrado";}
    }

}





