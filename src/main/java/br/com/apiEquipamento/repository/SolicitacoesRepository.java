package br.com.apiEquipamento.repository;

import br.com.apiEquipamento.model.Solicitacoes;
import br.com.apiEquipamento.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacoesRepository extends JpaRepository<Solicitacoes, Long> {
    List<Solicitacoes> findByUsuario(User user);
}
