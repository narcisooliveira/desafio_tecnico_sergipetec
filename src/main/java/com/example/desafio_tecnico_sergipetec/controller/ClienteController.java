package com.example.desafio_tecnico_sergipetec.controller;

import com.example.desafio_tecnico_sergipetec.dto.request.ClienteRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.ClienteResponseDTO;
import com.example.desafio_tecnico_sergipetec.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criar(
            @RequestBody @Valid ClienteRequestDTO dto) {

        return ResponseEntity.ok(clienteService.criarCliente(dto));
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

    @GetMapping(path = "/hello")
    public String HelloWorld(){
        return "Hello World!";
    }
}
