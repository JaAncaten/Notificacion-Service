package com.vetnova.notificacionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vetnova.notificacionservice.model.Notificacion;
import com.vetnova.notificacionservice.service.NotificacionService;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public List<Notificacion> obtenerNotificaciones() {
        return notificacionService.obtenerNotificaciones();
    }

    @GetMapping("/{id}")
    public Notificacion obtenerNotificacionPorId(@PathVariable Long id) {
        return notificacionService.obtenerNotificacionPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Notificacion> obtenerNotificacionesPorUsuario(@PathVariable Long usuarioId) {
        return notificacionService.obtenerNotificacionesPorUsuario(usuarioId);
    }

    @GetMapping("/estado/{estado}")
    public List<Notificacion> obtenerNotificacionesPorEstado(@PathVariable String estado) {
        return notificacionService.obtenerNotificacionesPorEstado(estado);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Notificacion> obtenerNotificacionesPorTipo(@PathVariable String tipo) {
        return notificacionService.obtenerNotificacionesPorTipo(tipo);
    }

    @PostMapping
    public Notificacion guardarNotificacion(@RequestBody Notificacion notificacion) {
        return notificacionService.guardarNotificacion(notificacion);
    }

    @PutMapping("/{id}")
    public Notificacion actualizarNotificacion(@PathVariable Long id,
                                               @RequestBody Notificacion notificacion) {
        return notificacionService.actualizarNotificacion(id, notificacion);
    }

    @DeleteMapping("/{id}")
    public String eliminarNotificacion(@PathVariable Long id) {
        boolean eliminada = notificacionService.eliminarNotificacion(id);

        if (eliminada) {
            return "Notificación eliminada correctamente";
        }

        return "Notificación no encontrada";
    }
}