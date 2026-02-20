package com.example.desafio_tecnico_sergipetec.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PedidoResponseDTO {

    private Long id;
    private Long clienteId;
    private LocalDateTime dataPedido;
    private BigDecimal valorTotal;
    private List<ItemResponseDTO> itens;

    @Getter
    @Builder
    public static class ItemResponseDTO {
        private Long produtoId;
        private String descricao;
        private BigDecimal valor;
        private Integer quantidade;
        private BigDecimal desconto;
    }
}
