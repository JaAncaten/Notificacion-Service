package com.vetnova.notificacionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vetnova.notificacionservice.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByUsuarioId(Long usuarioId);

    List<Notificacion> findByEstado(String estado);

    List<Notificacion> findByTipo(String tipo);
}