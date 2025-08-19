package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Aluno;
import application.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository alunoRepo;

    @PostMapping
    public Aluno insert(@RequestBody Aluno newStudent) {
        return alunoRepo.save(newStudent);
    }

    @GetMapping("/{id}")
    public Aluno getOne(@PathVariable long id) {
    Optional<Aluno> result = alunoRepo.findById(id);

    if (result.isEmpty()) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Aluno não encontrado"
        );
    }

    return result.get();
}

    @GetMapping
    public Iterable<Aluno> getAll() {
        return alunoRepo.findAll();
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable long id, @RequestBody Aluno newData) {
        Optional<Aluno> result = alunoRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }

        result.get().setNome(newData.getNome());
        result.get().setIdade(newData.getIdade());
        return alunoRepo.save(result.get());
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id){
        if(alunoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrado"
            );
        }   
        alunoRepo.deleteById(id);
    }

}
