package com.seuprojeto.desafio.repository;

import com.seuprojeto.desafio.entity.Cliente;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM cliente WHERE nome LIKE %:nome%", nativeQuery = true)
    List<Cliente> buscarPorNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM cliente WHERE id = :id", nativeQuery = true)
    Optional<Cliente> buscarPorId(@Param("id") Long id);
}
