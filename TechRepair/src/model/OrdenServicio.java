package model;

import java.time.LocalDate;
import java.util.List;

public record OrdenServicio(String codigo, LocalDate fecha, String observaciones, Estado estado, TechRepair ownedByTechRepair, Cliente cliente, List<Detalle> listaDetalles) {
}
