package com.exemplo.restaurante.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record PedidoRequestDTO(
        @NotNull @Positive Long clienteId,
        @NotNull @Positive Long enderecoId,
        @Positive Long entregadorId,
        @NotEmpty List<@NotNull @Valid ItemRequest> itens
) {

    public record ItemRequest(
            @NotNull @Positive Long produtoId,
            @NotNull @Positive Integer quantidade
    ) {
    }
}
