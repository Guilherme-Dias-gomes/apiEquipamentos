package br.com.apiEquipamento.dto;

import br.com.apiEquipamento.model.Solicitacoes;
import br.com.apiEquipamento.model.StatusSolicitacao;
import br.com.apiEquipamento.model.User;

import java.time.LocalDateTime;

public record SolicitResponseDTO(
        String titulo,
        String descricao,
        StatusSolicitacao status,
        LocalDateTime data,
        String nomeUsuario
) {
    public static SolicitResponseDTO from(Solicitacoes solicitacao) {
        return new SolicitResponseDTO(
                solicitacao.getTitulo(),
                solicitacao.getDescricao(),
                solicitacao.getStatus(),
                solicitacao.getData(),
                solicitacao.getUsuario().getNome()
        );
    }
}

