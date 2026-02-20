package com.example.desafio_tecnico_sergipetec.controller;

import com.example.desafio_tecnico_sergipetec.dto.request.ClienteRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.ClienteResponseDTO;
import com.example.desafio_tecnico_sergipetec.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cliente", description = "Operações relacionadas a cliente")
@RestController
@RequestMapping("/api/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(
            @RequestBody @Valid ClienteRequestDTO clienteDto) {

        return ResponseEntity.ok(clienteService.criarCliente(clienteDto));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar(
            @RequestParam(required = false) String nome) {

        if (nome != null) {
            return ResponseEntity.ok(clienteService.buscarPorNome(nome));
        }

        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
}
