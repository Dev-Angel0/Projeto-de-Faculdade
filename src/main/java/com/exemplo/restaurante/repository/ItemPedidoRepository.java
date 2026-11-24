package com.exemplo.restaurante.repository;

import com.exemplo.restaurante.model.ItemPedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findByPedido_Id(Long pedidoId);
}
