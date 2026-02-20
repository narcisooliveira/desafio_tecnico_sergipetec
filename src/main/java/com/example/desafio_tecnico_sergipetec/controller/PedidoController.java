package com.example.desafio_tecnico_sergipetec.controller;

import com.example.desafio_tecnico_sergipetec.dto.request.PedidoRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.PedidoResponseDTO;
import com.example.desafio_tecnico_sergipetec.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(
            @RequestBody @Valid PedidoRequestDTO dto) {

        return ResponseEntity.ok(pedidoService.criarPedido(dto));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoResponseDTO>> listarPorCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(pedidoService.listarPorCliente(clienteId));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<PedidoResponseDTO>> listarPorProduto(
            @PathVariable Long produtoId) {

        return ResponseEntity.ok(pedidoService.listarPorProduto(produtoId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<PedidoResponseDTO>> listarPorPeriodo(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fim) {

        return ResponseEntity.ok(pedidoService.listarPorPeriodo(inicio, fim));
    }

    @GetMapping("/total/{clienteId}")
    public ResponseEntity<Optional<Double>> totalPorCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(pedidoService.totalPorCliente(clienteId));
    }
}
