package com.example.desafio_tecnico_sergipetec.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ProdutoResponseDTO {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private Integer quantidadeEstoque;
    private LocalDateTime dataCadastro;
}
