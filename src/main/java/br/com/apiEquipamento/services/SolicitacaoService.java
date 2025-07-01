package br.com.apiEquipamento.services;

import br.com.apiEquipamento.dto.SolicitUpdateDTO;
import br.com.apiEquipamento.exception.SolicitNotFoundExcption;
import br.com.apiEquipamento.model.Solicitacoes;
import br.com.apiEquipamento.repository.SolicitacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoService {
    @Autowired
    SolicitacoesRepository repository;

    public List<Solicitacoes> getAllSolicit(){
        return repository.findAll();
    }

    public Solicitacoes getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new SolicitNotFoundExcption(id)
        );
    }

    public Solicitacoes novaSolicit(Solicitacoes solicitacoes){
        solicitacoes.setData(LocalDateTime.now());
        return repository.save(solicitacoes);
    }

    public Solicitacoes EditarSolicit(SolicitUpdateDTO solicitacoesDto, Long id){
        Solicitacoes solicitExistente = repository.findById(id).orElseThrow(
                () -> new SolicitNotFoundExcption(id)
        );
        solicitExistente.setTitulo(solicitacoesDto.titulo());
        solicitExistente.setDescricao(solicitacoesDto.descricao());
        solicitExistente.setStatus(solicitacoesDto.status());

        return repository.save(solicitExistente);
    }

    public void deleteSolicit(Long id){
        repository.findById(id).orElseThrow(
                () -> new SolicitNotFoundExcption(id)
        );
        repository.deleteById(id);
    }
}
