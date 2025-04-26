package dev.batist.ReadHub.controller;

import dev.batist.ReadHub.Entity.Book;
import dev.batist.ReadHub.Service.BookService;
import dev.batist.ReadHub.mapper.BookMapper;
import dev.batist.ReadHub.request.BookRequest;
import dev.batist.ReadHub.response.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/readhub/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> save(@RequestBody BookRequest bookRequest){
        Book newBook = BookMapper.toBook(bookRequest);
        Book save = bookService.save(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(BookMapper.toBookResponse(save));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll(){
        List<Book> books = bookService.getAll();
        return ResponseEntity.ok().body(books.stream()
                .map(BookMapper::toBookResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id){
        return bookService.findById(id).map(book -> ResponseEntity.ok().body(BookMapper.toBookResponse(book)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id, @RequestBody Map<String, Object> fields){
        return ResponseEntity.ok().body(bookService.update(id, fields));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
