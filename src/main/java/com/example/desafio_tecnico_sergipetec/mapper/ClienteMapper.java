package com.example.desafio_tecnico_sergipetec.mapper;

import com.example.desafio_tecnico_sergipetec.dto.request.ClienteRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.ClienteResponseDTO;
import com.example.desafio_tecnico_sergipetec.entity.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        return cliente;
    }

    public static ClienteResponseDTO toResponse(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .dataCadastro(cliente.getDataCadastro())
                .build();
    }
}
