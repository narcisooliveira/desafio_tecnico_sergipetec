package com.example.desafio_tecnico_sergipetec.repository;

import com.example.desafio_tecnico_sergipetec.entity.Produto;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "SELECT * FROM produto WHERE descricao LIKE %:descricao%", nativeQuery = true)
    List<Produto> buscarPorDescricao(@Param("descricao") String descricao);

    @Query(value = "SELECT * FROM produto WHERE id = :id", nativeQuery = true)
    Optional<Produto> buscarPorId(@Param("id") Long id);
}
