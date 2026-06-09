package com.acadimia.Controller;

import com.acadimia.Repository.AlunoRepository;
import com.acadimia.exception.CpfJaCadastradoException;
import com.acadimia.model.Aluno;
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
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody Aluno aluno) {
        if(repository.existsByCpf(aluno.getCpf())){
            throw new CpfJaCadastradoException(aluno.getCpf());
        }

        var salvo = repository.save(aluno);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@Valid @PathVariable Long id, @RequestBody Aluno aluno){
        Optional<Aluno> alunoExist = repository.findById(id);

        if(alunoExist.isPresent()){
            Aluno alunoAtualizado = alunoExist.get();

            alunoAtualizado.setNome(aluno.getNome());
            alunoAtualizado.setCpf(aluno.getCpf());
            alunoAtualizado.setDtNascimento(aluno.getDtNascimento());
            alunoAtualizado.setPlano(aluno.getPlano());
            alunoAtualizado.setMatriculaAtiva(aluno.getMatriculaAtiva());

            Aluno salvo = repository.save(alunoAtualizado);
            return ResponseEntity.ok(salvo);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAluno (@PathVariable Long id){
        Optional<Aluno> alunoExist = repository.findById(id);

        if(alunoExist.isPresent()){
            return ResponseEntity.ok(alunoExist.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deletar(@PathVariable Long id){
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}/matricula")
    public ResponseEntity<String> matricula (@PathVariable Long id){
        Optional<Aluno> alunoExist = repository.findById(id);

        if(!alunoExist.isPresent()){
            return ResponseEntity.notFound().build();
        }
        if(alunoExist.get().getMatriculaAtiva() == true){
            return ResponseEntity.ok("Matricula Ativa");
        }

        return ResponseEntity.ok("Matricula Não Ativada");

    }
}
