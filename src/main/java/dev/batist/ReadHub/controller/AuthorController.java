package dev.batist.ReadHub.controller;

import dev.batist.ReadHub.Entity.Author;
import dev.batist.ReadHub.Service.AuthorService;
import dev.batist.ReadHub.mapper.AuthorMapper;
import dev.batist.ReadHub.request.AuthorRequest;
import dev.batist.ReadHub.response.AuthorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/readhub/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> save(@RequestBody AuthorRequest request){
        Author newAuthor = AuthorMapper.toAuthor(request);
        Author save = authorService.save(newAuthor);
        return ResponseEntity.status(HttpStatus.CREATED).body(AuthorMapper.toAuthorResponse(save));
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> findAll(){
        List<Author> authors = authorService.getAll();
        return ResponseEntity.ok().body(authors.stream()
                .map(AuthorMapper::toAuthorResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable Long id){
        return authorService.getById(id)
                .map(author -> ResponseEntity.ok().body(AuthorMapper.toAuthorResponse(author)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Long id, @RequestBody Map<String, Object> fields){
        return ResponseEntity.status(HttpStatus.OK).body(authorService.update(id, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id){
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
