package dev.batist.ReadHub.Service;

import dev.batist.ReadHub.Entity.Author;
import dev.batist.ReadHub.Entity.Book;
import dev.batist.ReadHub.Entity.Formato;
import dev.batist.ReadHub.mapper.BookMapper;
import dev.batist.ReadHub.repository.BookRepository;
import dev.batist.ReadHub.response.BookResponse;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        Optional<Book> find = bookRepository.findById(id);
        return find;
    }

    @Transactional
    public BookResponse update(Long id, Map<String, Object> fields){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro nao encontrado"));
        fields.forEach((key, value) -> {

            switch (key){
                case "titulo" -> book.setTitulo((String) value);
                case "editora" -> book.setEditora((String) value);
                case "ISBN" -> book.setISBN((String) value);
                case "numberPages" -> book.setNumberPages((int) value);
                case "descricao" -> book.setDescricao((String) value);
                case "publicacao" -> book.setPublicacao(LocalDate.parse((String) value));
                case "img" -> book.setImg((String) value);
            }
        });
        Book bookAtualizado = bookRepository.save(book);
        return BookMapper.toBookResponse(bookAtualizado);
    }

    public void delete(Long id){
        bookRepository.deleteById(id);
    }
}
