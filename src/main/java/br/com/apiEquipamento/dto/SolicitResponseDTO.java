package br.com.apiEquipamento.dto;

import br.com.apiEquipamento.model.Solicitacoes;
import br.com.apiEquipamento.model.StatusSolicitacao;

import java.time.LocalDateTime;

public record SolicitResponseDTO(
        Long idSolicitacao,
        String titulo,
        String descricao,
        StatusSolicitacao status,
        LocalDateTime data,
        String nomeUsuario,
        boolean concluida
) {
    public static SolicitResponseDTO from(Solicitacoes solicitacao) {
        return new SolicitResponseDTO(
                solicitacao.getIdSolicitacao(),
                solicitacao.getTitulo(),
                solicitacao.getDescricao(),
                solicitacao.getStatus(),
                solicitacao.getData(),
                solicitacao.getUsuario().getNome(),
                solicitacao.isConcluida()
        );
    }
}

