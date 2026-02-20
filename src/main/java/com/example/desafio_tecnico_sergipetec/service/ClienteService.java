package com.example.desafio_tecnico_sergipetec.service;

import com.example.desafio_tecnico_sergipetec.dto.request.ClienteRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.ClienteResponseDTO;
import com.example.desafio_tecnico_sergipetec.entity.Cliente;
import com.example.desafio_tecnico_sergipetec.mapper.ClienteMapper;
import com.example.desafio_tecnico_sergipetec.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {

        Cliente cliente = ClienteMapper.toEntity(dto);
        cliente.setDataCadastro(LocalDateTime.now());

        cliente = clienteRepository.save(cliente);

        return ClienteMapper.toResponse(cliente);
    }

    public List<ClienteResponseDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return ClienteMapper.toResponse(cliente);
    }

    public List<ClienteResponseDTO> buscarPorNome(String nome) {
        return clienteRepository.buscarPorNome(nome)
                .stream()
                .map(ClienteMapper::toResponse)
                .collect(Collectors.toList());
    }
}
