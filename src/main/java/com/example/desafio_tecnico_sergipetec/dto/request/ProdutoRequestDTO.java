package com.example.desafio_tecnico_sergipetec.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequestDTO {

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer quantidadeEstoque;
}
