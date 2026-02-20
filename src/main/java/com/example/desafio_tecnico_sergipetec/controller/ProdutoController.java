package com.example.desafio_tecnico_sergipetec.controller;

import com.example.desafio_tecnico_sergipetec.dto.request.ProdutoRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.ProdutoResponseDTO;
import com.example.desafio_tecnico_sergipetec.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produto", description = "Operações relacionadas a produto")
@RestController
@RequestMapping("/api/v1/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(
            @RequestBody @Valid ProdutoRequestDTO produtoDto) {

        return ResponseEntity.ok(produtoService.criarProduto(produtoDto));
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
