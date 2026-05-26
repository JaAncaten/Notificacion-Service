package com.vetnova.notificacionservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vetnova.notificacionservice.model.Notificacion;
import com.vetnova.notificacionservice.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> obtenerNotificaciones() {
        return notificacionRepository.findAll();
    }

    public Notificacion obtenerNotificacionPorId(Long id) {
        return notificacionRepository.findById(id).orElse(null);
    }

    public List<Notificacion> obtenerNotificacionesPorUsuario(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public List<Notificacion> obtenerNotificacionesPorEstado(String estado) {
        return notificacionRepository.findByEstado(estado);
    }

    public List<Notificacion> obtenerNotificacionesPorTipo(String tipo) {
        return notificacionRepository.findByTipo(tipo);
    }

    public Notificacion guardarNotificacion(Notificacion notificacion) {
        notificacion.setFechaEnvio(LocalDateTime.now());

        if (notificacion.getEstado() == null || notificacion.getEstado().isBlank()) {
            notificacion.setEstado("PENDIENTE");
        }

        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizarNotificacion(Long id, Notificacion notificacionActualizada) {
        Optional<Notificacion> notificacionExistente = notificacionRepository.findById(id);

        if (notificacionExistente.isPresent()) {
            Notificacion notificacion = notificacionExistente.get();

            notificacion.setUsuarioId(notificacionActualizada.getUsuarioId());
            notificacion.setTitulo(notificacionActualizada.getTitulo());
            notificacion.setMensaje(notificacionActualizada.getMensaje());
            notificacion.setTipo(notificacionActualizada.getTipo());
            notificacion.setEstado(notificacionActualizada.getEstado());

            return notificacionRepository.save(notificacion);
        }

        return null;
    }

    public boolean eliminarNotificacion(Long id) {
        if (notificacionRepository.existsById(id)) {
            notificacionRepository.deleteById(id);
            return true;
        }

        return false;
    }
}