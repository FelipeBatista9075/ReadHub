package dev.batist.ReadHub.Service;

import dev.batist.ReadHub.Entity.Author;
import dev.batist.ReadHub.mapper.AuthorMapper;
import dev.batist.ReadHub.repository.AuthorRepository;
import dev.batist.ReadHub.request.AuthorRequest;
import dev.batist.ReadHub.response.AuthorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author){
        return authorRepository.save(author);
    }

    public List<Author> getAll(){
        return authorRepository.findAll();
    }

    public Optional<Author> getById(Long id){
        return authorRepository.findById(id);
    }

    public AuthorResponse update(Long id, Map<String, Object> fields){
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor nao encontrado"));

            fields.forEach((key, values) -> {
                switch (key){
                    case "name" -> author.setName((String) values);
                    case "bio" -> author.setBio((String) values);
                }
            });
            Author authorAtualizado = authorRepository.save(author);
            return AuthorMapper.toAuthorResponse(authorAtualizado);
    }

    public void delete(Long id){
        authorRepository.deleteById(id);
    }

}
