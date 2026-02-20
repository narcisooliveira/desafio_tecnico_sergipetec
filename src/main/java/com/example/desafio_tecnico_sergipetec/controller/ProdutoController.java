package com.example.desafio_tecnico_sergipetec.controller;

import com.example.desafio_tecnico_sergipetec.dto.request.ProdutoRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.ProdutoResponseDTO;
import com.example.desafio_tecnico_sergipetec.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(
            @RequestBody @Valid ProdutoRequestDTO dto) {

        return ResponseEntity.ok(produtoService.criarProduto(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar(
            @RequestParam(required = false) String descricao) {

        if (descricao != null) {
            return ResponseEntity.ok(produtoService.buscarPorDescricao(descricao));
        }

        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }
}
