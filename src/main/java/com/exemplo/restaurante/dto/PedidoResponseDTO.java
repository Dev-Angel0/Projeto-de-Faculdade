package com.exemplo.restaurante.dto;

import com.exemplo.restaurante.model.PedidoStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        PedidoStatus status,
        BigDecimal valorTotal,
        Long clienteId,
        Long enderecoId,
        Long entregadorId,
        List<ItemResponse> itens
) {

    public record ItemResponse(
            Long produtoId,
            String nomeProduto,
            Integer quantidade,
            BigDecimal precoUnitario,
            BigDecimal subtotal
    ) {
    }
}
