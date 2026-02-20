package com.example.desafio_tecnico_sergipetec.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataCadastro;
}
