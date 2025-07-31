package br.com.apiEquipamento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Solicitacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitacao;

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusSolicitacao status;

    private LocalDateTime data;

    private boolean concluida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false)
    private User usuario;
}
