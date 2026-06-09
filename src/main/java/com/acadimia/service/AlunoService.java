package com.acadimia.service;

import com.acadimia.Repository.AlunoRepository;
import com.acadimia.exception.CpfJaCadastradoException;
import com.acadimia.model.Aluno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTodos(){
        return alunoRepository.findAll();
    }

    public Aluno criarAluno(Aluno aluno) {
        if(alunoRepository.existsByCpf(aluno.getCpf())){
            throw new CpfJaCadastradoException(aluno.getCpf());
        }
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long id, Aluno aluno){
        Aluno alunoExist = alunoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        alunoExist.setNome(aluno.getNome());
        alunoExist.setCpf(aluno.getCpf());
        alunoExist.setDtNascimento(aluno.getDtNascimento());
        alunoExist.setPlano(aluno.getPlano());
        alunoExist.setMatriculaAtiva(aluno.getMatriculaAtiva());

        return alunoRepository.save(alunoExist);
    }

    public Aluno buscarAluno (Long id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public void deletar(Long id){
        alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        alunoRepository.deleteById(id);
    }
}
