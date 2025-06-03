package com.back_end_Journey.back_end_Journey.repository;
import com.back_end_Journey.back_end_Journey.model.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iPedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> { }

