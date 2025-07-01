package br.com.apiEquipamento.dto;

import br.com.apiEquipamento.model.StatusSolicitacao;

import java.time.LocalDateTime;

public record NovaSolicitacaoDTO(
        Long id,
        String titulo,
        String descricao,
        StatusSolicitacao status,
        LocalDateTime data,
        Long userId
) {}

