package com.example.desafio_tecnico_sergipetec.mapper;

import com.example.desafio_tecnico_sergipetec.dto.request.ProdutoRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.ProdutoResponseDTO;
import com.example.desafio_tecnico_sergipetec.entity.Produto;

public class ProdutoMapper {
    public static Produto toEntity(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setDescricao(dto.getDescricao());
        produto.setValor(dto.getValor());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        return produto;
    }

    public static ProdutoResponseDTO toResponse(Produto produto) {
        return ProdutoResponseDTO.builder()
                .id(produto.getId())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .dataCadastro(produto.getDataCadastro())
                .build();
    }
}
