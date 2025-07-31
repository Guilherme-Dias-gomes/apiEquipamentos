package br.com.apiEquipamento.dto;

import br.com.apiEquipamento.model.Solicitacoes;
import br.com.apiEquipamento.model.StatusSolicitacao;

public record SolicitUpdateDTO(
        String titulo,
        String descricao,
        StatusSolicitacao status,
        boolean concluida
) {}
