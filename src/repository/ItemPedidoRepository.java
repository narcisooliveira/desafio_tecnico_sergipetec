package com.seuprojeto.desafio.repository;

import com.seuprojeto.desafio.entity.ItemPedido;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    // Total de pedidos por cliente
    @Query(value = """
        SELECT SUM((ip.valor - ip.desconto) * ip.quantidade)
        FROM item_pedido ip
        JOIN pedido p ON p.id = ip.pedido_id
        WHERE p.cliente_id = :clienteId
    """, nativeQuery = true)
    Optional<Double> calcularTotalPedidosPorCliente(@Param("clienteId") Long clienteId);
}
