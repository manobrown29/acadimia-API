package com.acadimia.Controller;

import com.acadimia.Repository.AlunoRepository;
import com.acadimia.exception.CpfJaCadastradoException;
import com.acadimia.model.Aluno;
import com.acadimia.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> listarTodos() {
        return alunoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody Aluno aluno) {
        Aluno salvo = alunoService.criarAluno(aluno);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@Valid @PathVariable Long id, @RequestBody Aluno aluno){
        Aluno atualizado = alunoService.atualizar(id, aluno);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAluno (@PathVariable Long id){
        return ResponseEntity.ok(alunoService.buscarAluno(id));
    }

    @GetMapping("/{id}/matricula")
    public ResponseEntity<String> matricula (@PathVariable Long id){
        Aluno aluno = alunoService.buscarAluno(id);
        if(aluno.getMatriculaAtiva()){
            return ResponseEntity.ok("Matrícula ativa");
        }

        return ResponseEntity.ok("Matrícula inativa");
    }
}
