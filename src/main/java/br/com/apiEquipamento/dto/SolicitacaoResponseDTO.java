package br.com.apiEquipamento.dto;

import br.com.apiEquipamento.model.StatusSolicitacao;

import java.time.LocalDateTime;

public record SolicitacaoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        StatusSolicitacao status,
        LocalDateTime data,
        String nomeUsuario
) {}