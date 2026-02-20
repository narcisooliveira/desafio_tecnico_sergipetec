package com.example.desafio_tecnico_sergipetec.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoRequestDTO {

    @NotNull
    private Long clienteId;

    @NotEmpty
    private List<ItemPedidoDTO> itens;

    @Getter
    @Setter
    public static class ItemPedidoDTO {

        @NotNull
        private Long produtoId;

        @NotNull
        private Integer quantidade;

        private BigDecimal desconto;
    }
}
