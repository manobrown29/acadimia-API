package com.acadimia.Controller;

import com.acadimia.Repository.AlunoRepository;
import com.acadimia.exception.CpfJaCadastradoException;
import com.acadimia.model.Aluno;
import com.acadimia.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Endpoints de gerenciamento de alunos da academia")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @Operation(summary = "Listar todos os alunos", description = "Retorna uma lista completa de alunos cadastrados")
    public List<Aluno> listarTodos() {
        return alunoService.listarTodos();
    }

    @PostMapping
    @Operation(summary = "Cadastrar novos alunos", description = "Cadastra um aluno novo na academia")
    @ApiResponse(responseCode = "200", description = "Retorna o aluno salvo")
    @ApiResponse(responseCode = "409", description = "CPF já cadastrado")
    public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody Aluno aluno) {
        Aluno salvo = alunoService.criarAluno(aluno);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza aluno", description = "Atualiza todos o dados de um alno existente")
    public ResponseEntity<Aluno> atualizar(@Valid @PathVariable Long id, @RequestBody Aluno aluno){
        Aluno atualizado = alunoService.atualizar(id, aluno);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar aluno", description = "Deleta um aluno existente por id")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um aluno por id", description = "Busca um usuario existente por id")
    public ResponseEntity<Aluno> buscarAluno (@PathVariable Long id){
        return ResponseEntity.ok(alunoService.buscarAluno(id));
    }

    @GetMapping("/{id}/matricula")
    @Operation(summary = "Verifica status da matricula do aluno", description = "Verifica se a matricula do aluno está ativa")
    public ResponseEntity<String> matricula (@PathVariable Long id){
        Aluno aluno = alunoService.buscarAluno(id);
        if(aluno.getMatriculaAtiva()){
            return ResponseEntity.ok("Matrícula ativa");
        }

        return ResponseEntity.ok("Matrícula inativa");
    }
}
