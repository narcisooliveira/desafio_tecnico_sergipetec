package com.example.desafio_tecnico_sergipetec.service;

import com.example.desafio_tecnico_sergipetec.dto.request.PedidoRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.PedidoResponseDTO;
import com.example.desafio_tecnico_sergipetec.entity.Cliente;
import com.example.desafio_tecnico_sergipetec.entity.ItemPedido;
import com.example.desafio_tecnico_sergipetec.entity.Pedido;
import com.example.desafio_tecnico_sergipetec.entity.Produto;
import com.example.desafio_tecnico_sergipetec.mapper.PedidoMapper;
import com.example.desafio_tecnico_sergipetec.repository.ClienteRepository;
import com.example.desafio_tecnico_sergipetec.repository.ItemPedidoRepository;
import com.example.desafio_tecnico_sergipetec.repository.PedidoRepository;
import com.example.desafio_tecnico_sergipetec.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());

        pedido = pedidoRepository.save(pedido);

        List<ItemPedido> itensSalvos = new ArrayList<>();

        for (PedidoRequestDTO.ItemPedidoDTO itemDTO : dto.getItens()) {

            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getQuantidadeEstoque() < itemDTO.getQuantidade()) {
                throw new RuntimeException(
                        "Estoque insuficiente para o produto: " + produto.getDescricao()
                );
            }

            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque() - itemDTO.getQuantidade()
            );
            produtoRepository.save(produto);

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setValor(produto.getValor());
            item.setQuantidade(itemDTO.getQuantidade());

            BigDecimal desconto = itemDTO.getDesconto() != null
                    ? itemDTO.getDesconto()
                    : BigDecimal.ZERO;

            item.setDesconto(desconto);

            itensSalvos.add(itemPedidoRepository.save(item));
        }

        pedido.setItens(itensSalvos);

        return PedidoMapper.toResponse(pedido);
    }

    @Transactional
    public List<PedidoResponseDTO> listarPorCliente(Long clienteId) {

        List<Pedido> pedidos = pedidoRepository.buscarPedidosPorCliente(clienteId);

        return pedidos.stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }

    @Transactional
    public List<PedidoResponseDTO> listarPorProduto(Long produtoId) {

        List<Pedido> pedidos = pedidoRepository.buscarPedidosPorProduto(produtoId);

        return pedidos.stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }

    @Transactional
    public List<PedidoResponseDTO> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {

        List<Pedido> pedidos = pedidoRepository.buscarPorPeriodo(inicio, fim);

        return pedidos.stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }

    @Transactional
    public Optional<Double> totalPorCliente(Long clienteId) {
        return itemPedidoRepository.calcularTotalPedidosPorCliente(clienteId);
    }
}
