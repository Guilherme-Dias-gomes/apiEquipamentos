package br.com.apiEquipamento.controller;

import br.com.apiEquipamento.dto.NovaSolicitacaoDTO;
import br.com.apiEquipamento.dto.SolicitResponseDTO;
import br.com.apiEquipamento.dto.SolicitUpdateDTO;
import br.com.apiEquipamento.dto.SolicitacaoResponseDTO;
import br.com.apiEquipamento.model.Solicitacoes;
import br.com.apiEquipamento.model.User;
import br.com.apiEquipamento.repository.SolicitacoesRepository;
import br.com.apiEquipamento.repository.UserRepository;
import br.com.apiEquipamento.services.SolicitacaoService;
import br.com.apiEquipamento.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacoesController {

    @Autowired
    SolicitacaoService services;

    @Autowired
    SolicitacoesRepository repository;

    @Autowired
    UserServices userServices;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<SolicitResponseDTO> listarSolicitacoes(){
        return services.getAllSolicit().stream()
                .map(SolicitResponseDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SolicitResponseDTO solicitPorId(@PathVariable Long id){
        return SolicitResponseDTO.from(services.getById(id));
    }

    @GetMapping("/minhas-solicitacoes")
    public ResponseEntity<List<SolicitResponseDTO>> listarMinhasSolicitacoes(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);

        List<Solicitacoes> solicitacoes = repository.findByUsuario(user);
        List<SolicitResponseDTO> response = solicitacoes.stream()
                .map(SolicitResponseDTO::from)
                .toList();

        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<SolicitacaoResponseDTO> criarSolicit(@RequestBody @Valid NovaSolicitacaoDTO solicitDto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User usuario = userRepository.findByEmail(email);

        Solicitacoes novaSolicit = new Solicitacoes();
        novaSolicit.setTitulo(solicitDto.titulo());
        novaSolicit.setDescricao(solicitDto.descricao());
        novaSolicit.setStatus(solicitDto.status());
        novaSolicit.setData(LocalDateTime.now());
        novaSolicit.setUsuario(usuario);

        Solicitacoes solicitSalva = repository.save(novaSolicit);

        SolicitacaoResponseDTO response = new SolicitacaoResponseDTO(
                solicitSalva.getIdSolicitacao(),
                solicitSalva.getTitulo(),
                solicitSalva.getDescricao(),
                solicitSalva.getStatus(),
                solicitSalva.getData(),
                solicitSalva.getUsuario().getNome()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




    @PutMapping("/{id}")
    public ResponseEntity<SolicitUpdateDTO> editarSolicit(@RequestBody @Valid SolicitUpdateDTO solicitDTO, @PathVariable Long id ){
        Solicitacoes solicitAtualizada = services.EditarSolicit(solicitDTO, id);
        SolicitUpdateDTO response = new SolicitUpdateDTO(
                solicitAtualizada.getTitulo(),
                solicitAtualizada.getDescricao(),
                solicitAtualizada.getStatus()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public void deleteSolicit(@PathVariable Long id){
        services.deleteSolicit(id);
    }
}
