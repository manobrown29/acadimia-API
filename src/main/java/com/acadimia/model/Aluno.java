package com.acadimia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é Obrigatório") //não deixa o aluno ser null (só para texto)
    private String nome;

    @Column(unique = true)
    private String cpf;

    @NotNull(message = "A Data de nascimento é Obrigatória")
    private LocalDate dtNascimento;

    @NotNull(message = "A matrícula ativa é Obrigatório")
    private Boolean matriculaAtiva;

    @NotNull(message = "O plano é Obrigatório")
    @Enumerated(EnumType.STRING)
    private Plano plano;
}
