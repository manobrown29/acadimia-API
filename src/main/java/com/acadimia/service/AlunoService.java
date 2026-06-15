package com.acadimia.service;

import com.acadimia.Repository.AlunoRepository;
import com.acadimia.exception.AlunoNotFoundException;
import com.acadimia.exception.CpfJaCadastradoException;
import com.acadimia.exception.PlanoNotFound;
import com.acadimia.model.Aluno;
import com.acadimia.model.Plano;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
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
            .orElseThrow(() -> new AlunoNotFoundException(id));

        alunoExist.setNome(aluno.getNome());
        alunoExist.setCpf(aluno.getCpf());
        alunoExist.setDtNascimento(aluno.getDtNascimento());
        alunoExist.setPlano(aluno.getPlano());
        alunoExist.setMatriculaAtiva(aluno.getMatriculaAtiva());

        return alunoRepository.save(alunoExist);
    }

    public Aluno buscarAluno (Long id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException(id));
    }

    public void deletar(Long id){
        alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        alunoRepository.deleteById(id);
    }

    public Aluno atualizaPlano(Long id, String plano){
        Aluno aluno = buscarAluno(id);

        //Usar o valueOf da enum para transformar uma String em uma ENUM
        aluno.setPlano(validarPlano(plano));
        return alunoRepository.save(aluno);
    }

    //Função apenas para validar se a pessoa colocou um plano valido
    private Plano validarPlano(String plano){
        return Arrays.stream(Plano.values())
                .filter(p -> p.name().equalsIgnoreCase(plano))
                .findFirst()
                .orElseThrow(() -> new PlanoNotFound(plano));
    }
}
