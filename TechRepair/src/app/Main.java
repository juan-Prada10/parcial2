package app;

import model.Cliente;
import model.Compatibilidad;
import model.ComputadorEscritorio;
import model.Detalle;
import model.Diagnostico;
import model.EquipoTecnologico;
import model.Especialidad;
import model.Estado;
import model.EstadoLaboral;
import model.InventarioRepuesto;
import model.MantenimientoPreventivo;
import model.OrdenServicio;
import model.Portatil;
import model.ReparacionCorrectiva;
import model.RepuestoUsado;
import model.Servicio;
import model.TechRepair;
import model.Tecnico;
import model.TelefonoInteligente;
import model.TipoEquipo;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        TechRepair empresa = cargarDatos();
        mostrar("SISTEMA DE GESTIÓN TECH REPAIR\n\n"
                        + "NIT: " + empresa.getNit() + "\n"
                        + "Dirección: " + empresa.getDireccion() + "\n\n"
                        + "Se cargaron datos de demostración para probar el menú.",
                "Bienvenido");

        while (true) {
            Integer opcion = leerEntero(
                    "TECH REPAIR\n\n"
                            + "1. Registrar cliente\n"
                            + "2. Registrar equipo\n"
                            + "3. Registrar técnico\n"
                            + "4. Registrar repuesto\n"
                            + "5. Crear orden de servicio\n"
                            + "6. Agregar detalle a una orden\n"
                            + "7. Asignar técnico\n"
                            + "8. Registrar repuestos utilizados\n"
                            + "9. Calcular valor de un detalle\n"
                            + "10. Calcular valor total de una orden\n"
                            + "11. Actualizar estado de un detalle\n"
                            + "12. Finalizar una orden\n"
                            + "13. Consultar órdenes de un cliente\n"
                            + "14. Consultar trabajos de un técnico\n"
                            + "15. Consultar equipos pendientes\n"
                            + "16. Generar reporte de servicios\n"
                            + "17. Generar reporte de técnicos\n"
                            + "18. Consultar inventario crítico\n"
                            + "19. Salir\n\n"
                            + "Seleccione una opción:",
                    "Menú principal");
            if (opcion == null || opcion == 19) {
                mostrar("Gracias por usar Tech Repair.", "Sistema finalizado");
                return;
            }

            switch (opcion) {
                case 1 -> registrarCliente(empresa);
                case 2 -> registrarEquipo(empresa);
                case 3 -> registrarTecnico(empresa);
                case 4 -> registrarRepuesto(empresa);
                case 5 -> crearOrden(empresa);
                case 6 -> agregarDetalle(empresa);
                case 7 -> asignarTecnico(empresa);
                case 8 -> registrarRepuestoUsado(empresa);
                case 9 -> calcularValorDetalle(empresa);
                case 10 -> calcularValorOrden(empresa);
                case 11 -> actualizarEstadoDetalle(empresa);
                case 12 -> finalizarOrden(empresa);
                case 13 -> consultarOrdenesCliente(empresa);
                case 14 -> consultarTrabajosTecnico(empresa);
                case 15 -> consultarEquiposPendientes(empresa);
                case 16 -> generarReporteServicios(empresa);
                case 17 -> generarReporteTecnicos(empresa);
                case 18 -> consultarInventarioCritico(empresa);
                default -> mostrar("Opción no válida. Intente de nuevo.", "Atención");
            }
        }
    }

    public static TechRepair cargarDatos() {
        TechRepair empresa = new TechRepair("901.456.789-2", "Armenia, Quindío");

        Cliente cliente1 = new Cliente("1094900001", empresa, null, "Carrera 15 # 20-10",
                "carlos@example.com", "3001234567", "Carlos Gómez");
        Cliente cliente2 = new Cliente("1094900002", empresa, null, "Calle 8 # 12-30",
                "ana@example.com", "3107654321", "Ana López");
        Cliente cliente3 = new Cliente("1094900003", empresa, null, "Avenida Bolívar # 5-40",
                "luis@example.com", "3159876543", "Luis Pérez");
        empresa.registrarCliente(cliente1);
        empresa.registrarCliente(cliente2);
        empresa.registrarCliente(cliente3);

        EquipoTecnologico portatil = new Portatil("EQ-001", cliente1, 512, "Pantalla con rayones",
                LocalDate.of(2023, 1, 1), "Lenovo", "ThinkPad E14", "LEN-001",
                (byte) 14, (byte) 16, true);
        EquipoTecnologico escritorio = new ComputadorEscritorio("EQ-002", cliente2, 1000,
                "No enciende", LocalDate.of(2022, 1, 1), "HP", "Pavilion", "HP-002",
                "Intel Core i5", (byte) 16, true);
        EquipoTecnologico telefono = new TelefonoInteligente("EQ-003", cliente3, 256,
                "Batería de corta duración", LocalDate.of(2024, 1, 1), "Samsung",
                "Galaxy S23", "SAM-003", "Android", 3900, true);
        empresa.registrarEquipo(cliente1.getDocumento(), portatil);
        empresa.registrarEquipo(cliente2.getDocumento(), escritorio);
        empresa.registrarEquipo(cliente3.getDocumento(), telefono);

        Tecnico tecnico1 = new Tecnico("TEC-001", null, empresa, EstadoLaboral.ACTIVO,
                Especialidad.COMPUTADORES, "1001001", "Valentina Ruiz");
        Tecnico tecnico2 = new Tecnico("TEC-002", null, empresa, EstadoLaboral.ACTIVO,
                Especialidad.COMPUTADORES, "1001002", "Mateo Rojas");
        Tecnico tecnico3 = new Tecnico("TEC-003", null, empresa, EstadoLaboral.ACTIVO,
                Especialidad.DISPOSITIVOS_MOVILES, "1001003", "Sara Morales");
        empresa.registrarTecnico(tecnico1);
        empresa.registrarTecnico(tecnico2);
        empresa.registrarTecnico(tecnico3);

        InventarioRepuesto memoria = new InventarioRepuesto("REP-001", "Memoria RAM 8 GB",
                "Memoria DDR4", 85000, 8, Compatibilidad.ESCRITORIO, empresa);
        InventarioRepuesto bateria = new InventarioRepuesto("REP-002", "Batería para teléfono",
                "Batería de reemplazo", 120000, 3, Compatibilidad.TELEFONOS_INTELIGENTES, empresa);
        empresa.registrarRepuesto(memoria);
        empresa.registrarRepuesto(bateria);

        Servicio diagnostico = new Diagnostico("SER-001", "Diagnóstico",
                "Identificación de fallas del equipo", 35000, null, empresa);
        Servicio reparacion = new ReparacionCorrectiva("SER-002", "Reparación correctiva",
                "Reparación de fallas y cambio de repuestos", 45000, null, empresa);
        Servicio mantenimientoPortatil = new MantenimientoPreventivo("SER-003",
                "Mantenimiento preventivo de portátil", "Limpieza y revisión preventiva",
                60000, null, empresa, TipoEquipo.COMPUTADORES_PORTATILES);
        Servicio mantenimientoEscritorio = new MantenimientoPreventivo("SER-004",
                "Mantenimiento preventivo de escritorio", "Limpieza y revisión preventiva",
                70000, null, empresa, TipoEquipo.COMPUTADORES_ESCRITORIO);
        Servicio mantenimientoTelefono = new MantenimientoPreventivo("SER-005",
                "Mantenimiento preventivo de teléfono", "Limpieza y revisión preventiva",
                40000, null, empresa, TipoEquipo.TELEFONOS_INTELIGENTES);
        empresa.crearServicio(diagnostico);
        empresa.crearServicio(reparacion);
        empresa.crearServicioPreventivo((MantenimientoPreventivo) mantenimientoPortatil);
        empresa.crearServicioPreventivo((MantenimientoPreventivo) mantenimientoEscritorio);
        empresa.crearServicioPreventivo((MantenimientoPreventivo) mantenimientoTelefono);

        OrdenServicio orden1 = empresa.crearOrdenServicio("ORD-001", cliente1.getDocumento(),
                "Revisión inicial del portátil");
        OrdenServicio orden2 = empresa.crearOrdenServicio("ORD-002", cliente2.getDocumento(),
                "Reparación del computador de escritorio");
        OrdenServicio orden3 = empresa.crearOrdenServicio("ORD-003", cliente3.getDocumento(),
                "Revisión de batería del teléfono");
        cliente1.setOrdenServicio(orden1);
        cliente2.setOrdenServicio(orden2);
        cliente3.setOrdenServicio(orden3);

        Detalle detalle1 = crearDetalleDemostracion(empresa, orden1, portatil, diagnostico,
                "DET-001", "El equipo se calienta", tecnico1, Estado.EN_PROCESO, (byte) 0);
        Detalle detalle2 = crearDetalleDemostracion(empresa, orden2, escritorio, reparacion,
                "DET-002", "Falla en la memoria", tecnico2, Estado.FINALIZADA, (byte) 2);
        Detalle detalle3 = crearDetalleDemostracion(empresa, orden3, telefono, mantenimientoTelefono,
                "DET-003", "La batería se descarga rápidamente", tecnico3, Estado.ABIERTA, (byte) 0);

        if (detalle2 != null) {
            memoria.descontarRepuesto(1);
            ReparacionCorrectiva servicio = (ReparacionCorrectiva) detalle2.getServicio();
            servicio.registrarRepuestoUsado(new RepuestoUsado("RU-001", (byte) 1, memoria, servicio));
        }

        return empresa;
    }

    private static Detalle crearDetalleDemostracion(TechRepair empresa, OrdenServicio orden,
                                                    EquipoTecnologico equipo, Servicio plantilla, String codigo, String falla,
                                                    Tecnico tecnico, Estado estado, byte horas) {
        Servicio servicio = crearServicioDeUso(plantilla, codigo, empresa);
        Detalle detalle = new Detalle(codigo, equipo, null, orden, "Registro de demostración",
                estado, horas, falla, servicio);
        String resultado = empresa.agregarDetalle(orden.cliente().getDocumento(),
                orden.codigo(), detalle, servicio);
        if (!resultado.equals("Detalle creado con exito")) {
            return null;
        }
        servicio.setDetalle(detalle);
        empresa.asignarTecnico(tecnico, codigo);
        tecnico.setDetalle(detalle);
        return detalle;
    }

    private static Servicio crearServicioDeUso(Servicio plantilla, String codigoDetalle,
                                               TechRepair empresa) {
        String codigo = "USO-" + codigoDetalle;
        if (plantilla instanceof Diagnostico) {
            return new Diagnostico(codigo, plantilla.getNombre(), plantilla.getDescripcion(),
                    plantilla.getTarifaBase(), null, empresa);
        }
        if (plantilla instanceof ReparacionCorrectiva) {
            return new ReparacionCorrectiva(codigo, plantilla.getNombre(),
                    plantilla.getDescripcion(), plantilla.getTarifaBase(), null, empresa);
        }
        MantenimientoPreventivo mantenimiento = (MantenimientoPreventivo) plantilla;
        return new MantenimientoPreventivo(codigo, plantilla.getNombre(),
                plantilla.getDescripcion(), plantilla.getTarifaBase(), null, empresa,
                mantenimiento.getTipoEquipo());
    }

    private static void registrarCliente(TechRepair empresa) {
        String documento = leerTexto("Número de documento:");
        if (documento == null) return;
        String nombre = leerTexto("Nombre completo:");
        if (nombre == null) return;
        String telefono = leerTexto("Número telefónico:");
        if (telefono == null) return;
        String correo = leerTexto("Correo electrónico:");
        if (correo == null) return;
        String direccion = leerTexto("Dirección de residencia:");
        if (direccion == null) return;

        Cliente cliente = new Cliente(documento, empresa, null, direccion, correo, telefono, nombre);
        mostrar(empresa.registrarCliente(cliente)
                ? "Cliente registrado correctamente."
                : "No se registró el cliente: el documento ya existe.", "Registro de cliente");
    }

    private static void registrarEquipo(TechRepair empresa) {
        String documento = leerTexto("Documento del cliente:");
        if (documento == null) return;
        Optional<Cliente> clienteEncontrado = empresa.buscarClientePorDocumento(documento);
        if (clienteEncontrado.isEmpty()) {
            mostrar("No existe un cliente con ese documento.", "Registro de equipo");
            return;
        }

        String codigo = leerTexto("Código único del equipo:");
        if (codigo == null) return;
        String serie = leerTexto("Número de serie:");
        if (serie == null) return;
        String marca = leerTexto("Marca:");
        if (marca == null) return;
        String modelo = leerTexto("Modelo:");
        if (modelo == null) return;
        Integer año = leerEnteroEnRango("Año de fabricación:", 1, 9999);
        if (año == null) return;
        Integer almacenamiento = leerEnteroEnRango("Almacenamiento en GB:", 1, Integer.MAX_VALUE);
        if (almacenamiento == null) return;
        String estadoFisico = leerTexto("Descripción del estado físico:");
        if (estadoFisico == null) return;
        TipoEquipo tipo = leerEnum(TipoEquipo.class, "Tipo de equipo:");
        if (tipo == null) return;

        Cliente cliente = clienteEncontrado.get();
        LocalDate fechaFabricacion = LocalDate.of(año, 1, 1);
        EquipoTecnologico equipo;
        switch (tipo) {
            case COMPUTADORES_PORTATILES -> {
                Integer pantalla = leerEnteroEnRango(
                        "Tamaño de pantalla en pulgadas:", 1, Byte.MAX_VALUE);
                if (pantalla == null) return;
                Integer memoria = leerEnteroEnRango("Memoria RAM en GB:", 1, Byte.MAX_VALUE);
                if (memoria == null) return;
                Boolean bateria = leerBooleano("¿La batería funciona?");
                if (bateria == null) return;
                equipo = new Portatil(codigo, cliente, almacenamiento, estadoFisico,
                        fechaFabricacion, marca, modelo, serie, pantalla.byteValue(),
                        memoria.byteValue(), bateria);
            }
            case COMPUTADORES_ESCRITORIO -> {
                String procesador = leerTexto("Tipo de procesador:");
                if (procesador == null) return;
                Integer memoria = leerEnteroEnRango("Memoria RAM en GB:", 1, Byte.MAX_VALUE);
                if (memoria == null) return;
                Boolean tarjetaGrafica = leerBooleano("¿Tiene tarjeta gráfica?");
                if (tarjetaGrafica == null) return;
                equipo = new ComputadorEscritorio(codigo, cliente, almacenamiento, estadoFisico,
                        fechaFabricacion, marca, modelo, serie, procesador, memoria.byteValue(),
                        tarjetaGrafica);
            }
            case TELEFONOS_INTELIGENTES -> {
                String sistema = leerTexto("Sistema operativo:");
                if (sistema == null) return;
                Integer bateria = leerEnteroEnRango(
                        "Capacidad de batería en mAh:", 1, Integer.MAX_VALUE);
                if (bateria == null) return;
                Boolean cincoG = leerBooleano("¿Es compatible con 5G?");
                if (cincoG == null) return;
                equipo = new TelefonoInteligente(codigo, cliente, almacenamiento, estadoFisico,
                        fechaFabricacion, marca, modelo, serie, sistema, bateria, cincoG);
            }
            default -> throw new IllegalStateException("Tipo de equipo no reconocido.");
        }

        mostrar(empresa.registrarEquipo(documento, equipo)
                        ? "Equipo registrado correctamente."
                        : "No se registró el equipo. Verifique que su código sea único.",
                "Registro de equipo");
    }

    private static void registrarTecnico(TechRepair empresa) {
        String codigo = leerTexto("Código del técnico:");
        if (codigo == null) return;
        String nombre = leerTexto("Nombre completo:");
        if (nombre == null) return;
        String documento = leerTexto("Documento:");
        if (documento == null) return;
        Especialidad especialidad = leerEnum(Especialidad.class, "Especialidad:");
        if (especialidad == null) return;
        EstadoLaboral estado = leerEnum(EstadoLaboral.class, "Estado laboral:");
        if (estado == null) return;

        Tecnico tecnico = new Tecnico(codigo, null, empresa, estado, especialidad, documento, nombre);
        mostrar(empresa.registrarTecnico(tecnico)
                        ? "Técnico registrado correctamente."
                        : "No se registró el técnico: el código o documento ya existe.",
                "Registro de técnico");
    }

    private static void registrarRepuesto(TechRepair empresa) {
        String codigo = leerTexto("Código del repuesto:");
        if (codigo == null) return;
        String nombre = leerTexto("Nombre del repuesto:");
        if (nombre == null) return;
        String descripcion = leerTexto("Descripción:");
        if (descripcion == null) return;
        Double precio = leerDecimal("Precio unitario:");
        if (precio == null) return;
        Integer cantidad = leerEntero("Existencias iniciales:");
        if (cantidad == null) return;
        Compatibilidad compatibilidad = leerEnum(Compatibilidad.class, "Categoría compatible:");
        if (compatibilidad == null) return;

        InventarioRepuesto repuesto = new InventarioRepuesto(codigo, nombre, descripcion,
                precio, cantidad, compatibilidad, empresa);
        mostrar(empresa.registrarRepuesto(repuesto)
                        ? "Repuesto registrado correctamente."
                        : "No se registró el repuesto. Revise que el código no exista, "
                        + "el precio sea positivo y las existencias no sean negativas.",
                "Registro de repuesto");
    }

    private static void crearOrden(TechRepair empresa) {
        String codigo = leerTexto("Código de la nueva orden:");
        if (codigo == null) return;
        String documento = leerTexto("Documento del cliente:");
        if (documento == null) return;
        String observaciones = leerTexto("Observaciones generales:");
        if (observaciones == null) return;

        OrdenServicio orden = empresa.crearOrdenServicio(codigo, documento, observaciones);
        if (orden == null) {
            mostrar("No se creó la orden. Verifique el documento del cliente y que "
                    + "el código de orden no esté repetido.", "Crear orden");
            return;
        }
        empresa.buscarClientePorDocumento(documento).ifPresent(cliente -> cliente.setOrdenServicio(orden));
        mostrar("Orden " + orden.codigo() + " creada para "
                        + orden.cliente().getNombreCompleto() + ". Estado: " + nombreEstado(orden.estado()),
                "Crear orden");
    }

    private static void agregarDetalle(TechRepair empresa) {
        String codigoOrden = leerTexto("Código de la orden abierta:");
        if (codigoOrden == null) return;
        Optional<OrdenServicio> ordenEncontrada = empresa.buscarOrdenPorCodigo(codigoOrden);
        if (ordenEncontrada.isEmpty()) {
            mostrar("No se encontró la orden.", "Agregar detalle");
            return;
        }
        OrdenServicio orden = ordenEncontrada.get();
        if (orden.estado() != Estado.ABIERTA) {
            mostrar("Solo se pueden agregar detalles a órdenes abiertas.", "Agregar detalle");
            return;
        }

        String codigoEquipo = leerTexto("Código del equipo del cliente:");
        if (codigoEquipo == null) return;
        Optional<EquipoTecnologico> equipo = orden.cliente().getListaEquipoTecnologico().stream()
                .filter(item -> item.getCodigo().equalsIgnoreCase(codigoEquipo)).findFirst();
        if (equipo.isEmpty()) {
            mostrar("El equipo no está registrado a nombre del cliente de esta orden.",
                    "Agregar detalle");
            return;
        }

        mostrarCatalogoServicios(empresa);
        String codigoServicio = leerTexto("Código del servicio requerido:");
        if (codigoServicio == null) return;
        Optional<Servicio> plantilla = empresa.buscarServicio(codigoServicio);
        if (plantilla.isEmpty()) {
            mostrar("No se encontró el servicio.", "Agregar detalle");
            return;
        }
        if (!servicioCompatible(plantilla.get(), equipo.get())) {
            mostrar("El servicio preventivo seleccionado no corresponde al tipo de equipo.",
                    "Agregar detalle");
            return;
        }

        String codigoDetalle = leerTexto("Código único del detalle:");
        if (codigoDetalle == null) return;
        if (empresa.buscarDetalle(codigoDetalle).isPresent()) {
            mostrar("Ya existe un detalle con ese código.", "Agregar detalle");
            return;
        }
        String falla = leerTexto("Descripción de la falla o necesidad:");
        if (falla == null) return;
        String observaciones = leerTexto("Observaciones iniciales:");
        if (observaciones == null) return;
        Integer horas = leerEnteroEnRango(
                "Horas de trabajo (0 si aún no se han trabajado):", 0, Byte.MAX_VALUE);
        if (horas == null) return;

        Servicio servicio = crearServicioDeUso(plantilla.get(), codigoDetalle, empresa);
        Detalle detalle = new Detalle(codigoDetalle, equipo.get(), null, orden, observaciones,
                Estado.ABIERTA, horas.byteValue(), falla, servicio);
        String resultado = empresa.agregarDetalle(orden.cliente().getDocumento(),
                codigoOrden, detalle, servicio);
        if (resultado.equals("Detalle creado con exito")) {
            servicio.setDetalle(detalle);
            mostrar("Detalle " + codigoDetalle + " agregado a la orden " + codigoOrden + ".",
                    "Agregar detalle");
        } else {
            mostrar(resultado, "Agregar detalle");
        }
    }

    private static void asignarTecnico(TechRepair empresa) {
        String codigoDetalle = leerTexto("Código del detalle:");
        if (codigoDetalle == null) return;
        Optional<Detalle> detalleEncontrado = empresa.buscarDetalle(codigoDetalle);
        if (detalleEncontrado.isEmpty()) {
            mostrar("No se encontró el detalle.", "Asignar técnico");
            return;
        }
        String codigoTecnico = leerTexto("Código del técnico:");
        if (codigoTecnico == null) return;
        Optional<Tecnico> tecnicoEncontrado = empresa.buscarTecnicoPorCodigo(codigoTecnico);
        if (tecnicoEncontrado.isEmpty()) {
            mostrar("No se encontró el técnico.", "Asignar técnico");
            return;
        }

        Tecnico tecnico = tecnicoEncontrado.get();
        if (tecnico.getEstadoLaboral() != EstadoLaboral.ACTIVO) {
            mostrar("No se puede asignar un técnico inactivo.", "Asignar técnico");
            return;
        }
        if (!especialidadCompatible(tecnico.getEspecialidad(),
                detalleEncontrado.get().getEquipoTecnologico())) {
            mostrar("La especialidad del técnico no corresponde al equipo.",
                    "Asignar técnico");
            return;
        }

        mostrar(empresa.asignarTecnico(tecnico, codigoDetalle) + ".",
                "Asignar técnico");
        tecnico.setDetalle(detalleEncontrado.get());
    }

    private static void registrarRepuestoUsado(TechRepair empresa) {
        String codigoDetalle = leerTexto("Código del detalle de reparación:");
        if (codigoDetalle == null) return;
        Optional<Detalle> detalleEncontrado = empresa.buscarDetalle(codigoDetalle);
        if (detalleEncontrado.isEmpty()) {
            mostrar("No se encontró el detalle.", "Registrar repuesto utilizado");
            return;
        }
        if (!(detalleEncontrado.get().getServicio() instanceof ReparacionCorrectiva reparacion)) {
            mostrar("Los repuestos solo se pueden agregar a una reparación correctiva.",
                    "Registrar repuesto utilizado");
            return;
        }

        String codigoRepuesto = leerTexto("Código del repuesto:");
        if (codigoRepuesto == null) return;
        Optional<InventarioRepuesto> repuestoEncontrado =
                empresa.buscarRepuestoPorCodigo(codigoRepuesto);
        if (repuestoEncontrado.isEmpty()) {
            mostrar("No se encontró el repuesto.", "Registrar repuesto utilizado");
            return;
        }
        InventarioRepuesto repuesto = repuestoEncontrado.get();
        if (!repuestoCompatible(repuesto.getCategoriaCompatibilidad(),
                detalleEncontrado.get().getEquipoTecnologico())) {
            mostrar("El repuesto no es compatible con el equipo intervenido.",
                    "Registrar repuesto utilizado");
            return;
        }
        Integer cantidad = leerEnteroEnRango("Cantidad a utilizar (disponible: "
                + repuesto.getCantidadDisponible() + "):", 1, Byte.MAX_VALUE);
        if (cantidad == null) return;
        if (!repuesto.descontarRepuesto(cantidad)) {
            mostrar("No hay existencias suficientes para esa cantidad.",
                    "Registrar repuesto utilizado");
            return;
        }

        String codigoUso = "RU-" + codigoDetalle + "-" + reparacion.getListaRepuestosUsados().size();
        reparacion.registrarRepuestoUsado(new RepuestoUsado(codigoUso, cantidad.byteValue(),
                repuesto, reparacion));
        mostrar("Repuesto agregado. Existencias restantes: " + repuesto.getCantidadDisponible(),
                "Registrar repuesto utilizado");
    }

    private static void calcularValorDetalle(TechRepair empresa) {
        String codigo = leerTexto("Código del detalle:");
        if (codigo == null) return;
        Optional<Detalle> detalle = empresa.buscarDetalle(codigo);
        if (detalle.isEmpty()) {
            mostrar("No se encontró el detalle.", "Valor del detalle");
            return;
        }
        mostrar("Valor del detalle " + codigo + ": "
                        + formatoMoneda(detalle.get().calcularSubTotalDetalle()),
                "Valor del detalle");
    }

    private static void calcularValorOrden(TechRepair empresa) {
        String codigo = leerTexto("Código de la orden:");
        if (codigo == null) return;
        Optional<OrdenServicio> orden = empresa.buscarOrdenPorCodigo(codigo);
        if (orden.isEmpty()) {
            mostrar("No se encontró la orden.", "Valor de la orden");
            return;
        }
        mostrar("Valor total de la orden " + codigo + ": "
                        + formatoMoneda(orden.get().caocularTotalOrden()),
                "Valor de la orden");
    }

    private static void actualizarEstadoDetalle(TechRepair empresa) {
        String codigo = leerTexto("Código del detalle:");
        if (codigo == null) return;
        Estado estado = leerEnum(Estado.class, "Nuevo estado del detalle:");
        if (estado == null) return;
        mostrar(empresa.actualizarDetalle(codigo, estado) + ".",
                "Actualizar estado");
    }

    private static void finalizarOrden(TechRepair empresa) {
        String codigo = leerTexto("Código de la orden que desea finalizar:");
        if (codigo == null) return;
        Optional<OrdenServicio> ordenEncontrada = empresa.buscarOrdenPorCodigo(codigo);
        if (ordenEncontrada.isEmpty()) {
            mostrar("No se encontró la orden.", "Finalizar orden");
            return;
        }
        OrdenServicio orden = ordenEncontrada.get();
        if (orden.listaDetalles().isEmpty()
                || orden.listaDetalles().stream().anyMatch(detalle ->
                detalle.getEstado() != Estado.FINALIZADA)) {
            mostrar("No se puede finalizar la orden: todos sus detalles deben estar finalizados.",
                    "Finalizar orden");
            return;
        }

        int indice = empresa.getListaOrdenServicio().indexOf(orden);
        OrdenServicio ordenFinalizada = new OrdenServicio(orden.codigo(), orden.fecha(),
                orden.observaciones(), Estado.FINALIZADA, orden.ownedByTechRepair(),
                orden.cliente(), orden.listaDetalles());
        empresa.getListaOrdenServicio().set(indice, ordenFinalizada);
        ordenFinalizada.listaDetalles().forEach(detalle ->
                detalle.setOwnedByOrdenServicio(ordenFinalizada));
        orden.cliente().setOrdenServicio(ordenFinalizada);
        mostrar("La orden " + codigo + " fue finalizada correctamente.",
                "Finalizar orden");
    }

    private static void consultarOrdenesCliente(TechRepair empresa) {
        String documento = leerTexto("Documento del cliente:");
        if (documento == null) return;
        Optional<Cliente> cliente = empresa.buscarClientePorDocumento(documento);
        if (cliente.isEmpty()) {
            mostrar("No se encontró el cliente.", "Órdenes del cliente");
            return;
        }
        List<OrdenServicio> ordenes = empresa.getListaOrdenServicio().stream()
                .filter(orden -> orden.cliente().getDocumento().equalsIgnoreCase(documento))
                .toList();
        if (ordenes.isEmpty()) {
            mostrar("El cliente todavía no tiene órdenes de servicio.", "Órdenes del cliente");
            return;
        }
        StringBuilder reporte = new StringBuilder("Órdenes de ")
                .append(cliente.get().getNombreCompleto()).append(":\n\n");
        for (OrdenServicio orden : ordenes) {
            reporte.append("Orden: ").append(orden.codigo())
                    .append(" | Fecha: ").append(orden.fecha())
                    .append(" | Estado: ").append(nombreEstado(orden.estado()))
                    .append(" | Detalles: ").append(orden.listaDetalles().size())
                    .append(" | Total: ").append(formatoMoneda(orden.caocularTotalOrden()))
                    .append('\n');
        }
        mostrar(reporte.toString(), "Órdenes del cliente");
    }

    private static void consultarTrabajosTecnico(TechRepair empresa) {
        String codigo = leerTexto("Código del técnico:");
        if (codigo == null) return;
        Optional<Tecnico> tecnicoEncontrado = empresa.buscarTecnicoPorCodigo(codigo);
        if (tecnicoEncontrado.isEmpty()) {
            mostrar("No se encontró el técnico.", "Trabajos del técnico");
            return;
        }
        Tecnico tecnico = tecnicoEncontrado.get();
        List<Detalle> trabajos = obtenerDetalles(empresa).stream()
                .filter(detalle -> detalle.getTecnico() != null
                        && detalle.getTecnico().getCodigo().equalsIgnoreCase(codigo))
                .toList();
        if (trabajos.isEmpty()) {
            mostrar("El técnico todavía no tiene trabajos asignados.", "Trabajos del técnico");
            return;
        }
        StringBuilder reporte = new StringBuilder("Trabajos de ")
                .append(tecnico.getNombre()).append(":\n\n");
        for (Detalle detalle : trabajos) {
            reporte.append("Detalle: ").append(detalle.getCodigo())
                    .append(" | Equipo: ").append(detalle.getEquipoTecnologico().getCodigo())
                    .append(" | Estado: ").append(nombreEstado(detalle.getEstado()))
                    .append(" | Valor: ").append(formatoMoneda(detalle.calcularSubTotalDetalle()))
                    .append('\n');
        }
        mostrar(reporte.toString(), "Trabajos del técnico");
    }

    private static void consultarEquiposPendientes(TechRepair empresa) {
        List<Detalle> pendientes = obtenerDetalles(empresa).stream()
                .filter(detalle -> detalle.getEstado() != Estado.FINALIZADA
                        && detalle.getEstado() != Estado.ENTREGADA
                        && detalle.getEstado() != Estado.CANCELADA)
                .toList();
        if (pendientes.isEmpty()) {
            mostrar("No hay equipos con intervenciones pendientes.", "Equipos pendientes");
            return;
        }
        StringBuilder reporte = new StringBuilder("Intervenciones pendientes:\n\n");
        for (Detalle detalle : pendientes) {
            reporte.append("Orden: ").append(detalle.getOwnedByOrdenServicio().codigo())
                    .append(" | Detalle: ").append(detalle.getCodigo())
                    .append(" | Equipo: ").append(detalle.getEquipoTecnologico().getCodigo())
                    .append(" | Cliente: ")
                    .append(detalle.getOwnedByOrdenServicio().cliente().getNombreCompleto())
                    .append(" | Estado: ").append(nombreEstado(detalle.getEstado()))
                    .append('\n');
        }
        mostrar(reporte.toString(), "Equipos pendientes");
    }

    private static void generarReporteServicios(TechRepair empresa) {
        Map<String, double[]> totalesPorTipo = new LinkedHashMap<>();
        for (Detalle detalle : obtenerDetalles(empresa)) {
            String tipo = nombreTipoServicio(detalle.getServicio());
            double[] resumen = totalesPorTipo.computeIfAbsent(tipo, clave -> new double[2]);
            resumen[0]++;
            resumen[1] += detalle.calcularSubTotalDetalle();
        }
        if (totalesPorTipo.isEmpty()) {
            mostrar("No hay servicios registrados para generar el reporte.",
                    "Reporte de servicios");
            return;
        }
        StringBuilder reporte = new StringBuilder("SERVICIOS POR CATEGORÍA\n\n");
        for (Map.Entry<String, double[]> entrada : totalesPorTipo.entrySet()) {
            reporte.append(entrada.getKey())
                    .append(" | Cantidad: ").append((int) entrada.getValue()[0])
                    .append(" | Total facturado: ")
                    .append(formatoMoneda(entrada.getValue()[1])).append('\n');
        }
        mostrar(reporte.toString(), "Reporte de servicios");
    }

    private static void generarReporteTecnicos(TechRepair empresa) {
        StringBuilder reporte = new StringBuilder("INTERVENCIONES POR TÉCNICO\n\n");
        for (Tecnico tecnico : empresa.getListaTecnicos()) {
            List<Detalle> trabajos = obtenerDetalles(empresa).stream()
                    .filter(detalle -> detalle.getTecnico() != null
                            && detalle.getTecnico().getCodigo().equals(tecnico.getCodigo()))
                    .toList();
            long finalizados = trabajos.stream()
                    .filter(detalle -> detalle.getEstado() == Estado.FINALIZADA
                            || detalle.getEstado() == Estado.ENTREGADA)
                    .count();
            double valorAtendido = trabajos.stream()
                    .mapToDouble(Detalle::calcularSubTotalDetalle).sum();
            reporte.append(tecnico.getNombre())
                    .append(" | Especialidad: ").append(nombreEnum(tecnico.getEspecialidad()))
                    .append(" | Intervenciones finalizadas: ").append(finalizados)
                    .append(" | Valor de servicios atendidos: ")
                    .append(formatoMoneda(valorAtendido)).append('\n');
        }
        mostrar(reporte.toString(), "Reporte de técnicos");
    }

    private static void consultarInventarioCritico(TechRepair empresa) {
        List<InventarioRepuesto> criticos = empresa.repuestosCriticos();
        if (criticos.isEmpty()) {
            mostrar("No hay repuestos con menos de cinco unidades.",
                    "Inventario crítico");
            return;
        }
        StringBuilder reporte = new StringBuilder("REPUESTOS CON EXISTENCIAS INFERIORES A 5\n\n");
        for (InventarioRepuesto repuesto : criticos) {
            reporte.append(repuesto.getCodigo()).append(" | ")
                    .append(repuesto.getNombre())
                    .append(" | Disponibles: ").append(repuesto.getCantidadDisponible())
                    .append(" | Precio unitario: ")
                    .append(formatoMoneda(repuesto.getPrecioUnitario())).append('\n');
        }
        mostrar(reporte.toString(), "Inventario crítico");
    }

    private static List<Detalle> obtenerDetalles(TechRepair empresa) {
        List<Detalle> detalles = new ArrayList<>();
        for (OrdenServicio orden : empresa.getListaOrdenServicio()) {
            detalles.addAll(orden.listaDetalles());
        }
        return detalles;
    }

    private static void mostrarCatalogoServicios(TechRepair empresa) {
        StringBuilder catalogo = new StringBuilder("Servicios disponibles:\n\n");
        for (Servicio servicio : empresa.getListaServicios()) {
            catalogo.append(servicio.getCodigo()).append(" | ")
                    .append(servicio.getNombre()).append(" | Tarifa base: ")
                    .append(formatoMoneda(servicio.getTarifaBase())).append('\n');
        }
        mostrar(catalogo.toString(), "Catálogo de servicios");
    }

    private static boolean servicioCompatible(Servicio servicio, EquipoTecnologico equipo) {
        if (!(servicio instanceof MantenimientoPreventivo mantenimiento)) return true;
        return (equipo instanceof Portatil
                && mantenimiento.getTipoEquipo() == TipoEquipo.COMPUTADORES_PORTATILES)
                || (equipo instanceof ComputadorEscritorio
                && mantenimiento.getTipoEquipo() == TipoEquipo.COMPUTADORES_ESCRITORIO)
                || (equipo instanceof TelefonoInteligente
                && mantenimiento.getTipoEquipo() == TipoEquipo.TELEFONOS_INTELIGENTES);
    }

    private static boolean especialidadCompatible(Especialidad especialidad,
                                                  EquipoTecnologico equipo) {
        return especialidad == Especialidad.SOPORTE_GENERAL
                || (especialidad == Especialidad.COMPUTADORES
                && (equipo instanceof Portatil || equipo instanceof ComputadorEscritorio))
                || (especialidad == Especialidad.DISPOSITIVOS_MOVILES
                && equipo instanceof TelefonoInteligente);
    }

    private static boolean repuestoCompatible(Compatibilidad compatibilidad,
                                              EquipoTecnologico equipo) {
        return (compatibilidad == Compatibilidad.PORTATIL && equipo instanceof Portatil)
                || (compatibilidad == Compatibilidad.ESCRITORIO
                && equipo instanceof ComputadorEscritorio)
                || (compatibilidad == Compatibilidad.TELEFONOS_INTELIGENTES
                && equipo instanceof TelefonoInteligente);
    }

    private static String nombreTipoServicio(Servicio servicio) {
        if (servicio instanceof Diagnostico) return "Diagnóstico";
        if (servicio instanceof ReparacionCorrectiva) return "Reparación correctiva";
        return "Mantenimiento preventivo";
    }

    private static String nombreEstado(Estado estado) {
        return switch (estado) {
            case ABIERTA -> "Abierta";
            case EN_PROCESO -> "En proceso";
            case FINALIZADA -> "Finalizada";
            case ENTREGADA -> "Entregada";
            case CANCELADA -> "Cancelada";
        };
    }

    private static String nombreEnum(Enum<?> valor) {
        return valor.name().replace('_', ' ');
    }

    private static <E extends Enum<E>> E leerEnum(Class<E> tipo, String mensaje) {
        E[] valores = tipo.getEnumConstants();
        StringBuilder opciones = new StringBuilder(mensaje).append("\n\n");
        for (int indice = 0; indice < valores.length; indice++) {
            opciones.append(indice + 1).append(". ")
                    .append(nombreEnum(valores[indice])).append('\n');
        }
        Integer opcion = leerEntero(opciones.toString(), "Seleccione una opción:");
        if (opcion == null) return null;
        if (opcion < 1 || opcion > valores.length) {
            mostrar("La opción seleccionada no es válida.", "Dato no válido");
            return null;
        }
        return valores[opcion - 1];
    }

    private static Boolean leerBooleano(String mensaje) {
        int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Confirmación",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (opcion == JOptionPane.CLOSED_OPTION || opcion == JOptionPane.CANCEL_OPTION) return null;
        return opcion == JOptionPane.YES_OPTION;
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            String valor = JOptionPane.showInputDialog(null, mensaje, "Tech Repair",
                    JOptionPane.QUESTION_MESSAGE);
            if (valor == null) return null;
            valor = valor.trim();
            if (!valor.isEmpty()) return valor;
            mostrar("El valor no puede estar vacío.", "Dato no válido");
        }
    }

    private static Integer leerEntero(String mensaje) {
        return leerEntero(mensaje, "Tech Repair");
    }

    private static Integer leerEntero(String mensaje, String titulo) {
        while (true) {
            String valor = JOptionPane.showInputDialog(null, mensaje, titulo,
                    JOptionPane.QUESTION_MESSAGE);
            if (valor == null) return null;
            try {
                return Integer.valueOf(valor.trim());
            } catch (NumberFormatException excepcion) {
                mostrar("Ingrese un número entero válido.", "Dato no válido");
            }
        }
    }

    private static Integer leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        while (true) {
            Integer valor = leerEntero(mensaje + "\n(" + minimo + " a " + maximo + ")");
            if (valor == null) return null;
            if (valor >= minimo && valor <= maximo) return valor;
            mostrar("El valor debe estar entre " + minimo + " y " + maximo + ".",
                    "Dato no válido");
        }
    }

    private static Double leerDecimal(String mensaje) {
        while (true) {
            String valor = JOptionPane.showInputDialog(null, mensaje, "Tech Repair",
                    JOptionPane.QUESTION_MESSAGE);
            if (valor == null) return null;
            try {
                double numero = Double.parseDouble(valor.trim().replace(',', '.'));
                if (Double.isFinite(numero)) return numero;
            } catch (NumberFormatException excepcion) {
                mostrar("Ingrese un valor numérico válido.", "Dato no válido");
                continue;
            }
            mostrar("Ingrese un valor numérico válido.", "Dato no válido");
        }
    }

    private static String formatoMoneda(double valor) {
        return String.format("$%,.0f", valor);
    }

    private static void mostrar(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
