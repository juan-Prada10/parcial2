package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public record OrdenServicio(String codigo, LocalDate fecha, String observaciones, Estado estado, TechRepair ownedByTechRepair, Cliente cliente, List<Detalle> listaDetalles) {

    public Optional<Detalle> buscarDetalle(String codigo){
        return listaDetalles.stream().filter(detalle -> detalle.getCodigo().equalsIgnoreCase(codigo)).findFirst();
    }

    public String crearDetalle(Detalle detalle){
        Optional<Detalle> detalleEncontrado= buscarDetalle(detalle.getCodigo());
        if (detalleEncontrado.isEmpty()) {
            listaDetalles.add(detalle);
            return "Detalle creado con exito";
        }else{return "el detalle ya existe";}
    }

    public String eliminiarDetalle(String codigo){
        Optional<Detalle> detalleEncontrado=buscarDetalle(codigo);
        if (detalleEncontrado.isPresent()){
            listaDetalles.remove(detalleEncontrado.get());
            return "Detalle eliminado con exito";
        }
        return "El detalle no existe";
    }

    public String actualizarDetalle(String codigo,EquipoTecnologico nuevoEquipoTecnologico, Tecnico nuevoTecnico, String nuevaObservaciones, Estado nuevoEstado, byte nuevasHorasTrabajo, String nuevaDescripcionFalla){
        Optional<Detalle> detalleEncontrado = buscarDetalle(codigo);
        if (detalleEncontrado.isPresent()){
            detalleEncontrado.get().setEquipoTecnologico(nuevoEquipoTecnologico);
            detalleEncontrado.get().setTecnico(nuevoTecnico);
            detalleEncontrado.get().setObservaciones(nuevaObservaciones);
            detalleEncontrado.get().setEstado(nuevoEstado);
            detalleEncontrado.get().setHorasTrabajo(nuevasHorasTrabajo);
            detalleEncontrado.get().setDescripcionFalla(nuevaDescripcionFalla);

            return "Detalle actualizado con exito";
        }else{return "El detalle no existe";}
    }

    public double caocularTotalOrden(){
        double totalDetalle = 0;
        for(Detalle detalles :listaDetalles){
            totalDetalle+=detalles.calcularSubTotalDetalle();
        }
        return totalDetalle;
    }


}
