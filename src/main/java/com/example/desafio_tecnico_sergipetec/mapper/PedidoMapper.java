package com.example.desafio_tecnico_sergipetec.mapper;

import com.example.desafio_tecnico_sergipetec.dto.response.PedidoResponseDTO;
import com.example.desafio_tecnico_sergipetec.entity.Pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {
    public static PedidoResponseDTO toResponse(Pedido pedido) {

        List<PedidoResponseDTO.ItemResponseDTO> itens =
                pedido.getItens().stream()
                        .map(item -> PedidoResponseDTO.ItemResponseDTO.builder()
                                .produtoId(item.getProduto().getId())
                                .descricao(item.getProduto().getDescricao())
                                .valor(item.getValor())
                                .quantidade(item.getQuantidade())
                                .desconto(item.getDesconto())
                                .build())
                        .collect(Collectors.toList());

        BigDecimal total = pedido.getItens().stream()
                .map(item ->
                        item.getValor()
                                .subtract(item.getDesconto())
                                .multiply(BigDecimal.valueOf(item.getQuantidade()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return PedidoResponseDTO.builder()
                .id(pedido.getId())
                .clienteId(pedido.getCliente().getId())
                .dataPedido(pedido.getDataPedido())
                .valorTotal(total)
                .itens(itens)
                .build();
    }
}
