package br.com.apiEquipamento.repository;

import br.com.apiEquipamento.model.Solicitacoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacoesRepository extends JpaRepository<Solicitacoes, Long> {
}
