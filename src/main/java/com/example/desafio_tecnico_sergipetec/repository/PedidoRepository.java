package com.example.desafio_tecnico_sergipetec.repository;

import com.example.desafio_tecnico_sergipetec.entity.Pedido;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Listar pedidos de um cliente
    @Query(value = """
        SELECT * FROM pedido
        WHERE cliente_id = :clienteId
    """, nativeQuery = true)
    List<Pedido> buscarPedidosPorCliente(@Param("clienteId") Long clienteId);

    // Listar pedidos que contenham um produto
    @Query(value = """
        SELECT DISTINCT p.*
        FROM pedido p
        JOIN item_pedido ip ON ip.pedido_id = p.id
        WHERE ip.produto_id = :produtoId
    """, nativeQuery = true)
    List<Pedido> buscarPedidosPorProduto(@Param("produtoId") Long produtoId);

    // Buscar por período
    @Query(value = """
        SELECT * FROM pedido
        WHERE data_pedido BETWEEN :inicio AND :fim
    """, nativeQuery = true)
    List<Pedido> buscarPorPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );

    // Buscar por id
    @Query(value = "SELECT * FROM pedido WHERE id = :id", nativeQuery = true)
    Pedido buscarPorId(@Param("id") Long id);
}
