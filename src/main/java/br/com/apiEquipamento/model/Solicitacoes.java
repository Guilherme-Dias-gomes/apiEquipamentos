package br.com.apiEquipamento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Long idSolicitacao;

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;

    private LocalDateTime data;

    @Column(name = "concluida", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean concluida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false)
    private User usuario;
}