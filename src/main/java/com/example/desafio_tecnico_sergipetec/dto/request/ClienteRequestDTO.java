package com.example.desafio_tecnico_sergipetec.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;
}
