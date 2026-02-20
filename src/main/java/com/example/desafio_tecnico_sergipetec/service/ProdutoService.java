package com.example.desafio_tecnico_sergipetec.service;

import com.example.desafio_tecnico_sergipetec.dto.request.ProdutoRequestDTO;
import com.example.desafio_tecnico_sergipetec.dto.response.ProdutoResponseDTO;
import com.example.desafio_tecnico_sergipetec.entity.Produto;
import com.example.desafio_tecnico_sergipetec.mapper.ProdutoMapper;
import com.example.desafio_tecnico_sergipetec.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {

        Produto produto = ProdutoMapper.toEntity(dto);
        produto.setDataCadastro(LocalDateTime.now());

        produto = produtoRepository.save(produto);

        return ProdutoMapper.toResponse(produto);
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return ProdutoMapper.toResponse(produto);
    }

    public List<ProdutoResponseDTO> buscarPorDescricao(String descricao) {
        return produtoRepository.buscarPorDescricao(descricao)
                .stream()
                .map(ProdutoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
