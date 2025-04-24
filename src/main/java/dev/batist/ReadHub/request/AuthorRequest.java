package dev.batist.ReadHub.request;

import dev.batist.ReadHub.Entity.Book;

import java.util.List;

public class AuthorRequest {
    private Long id;
    private String name;
    private String bio;
    private List<Book> booksAutor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Book> getBooksAutor() {
        return booksAutor;
    }

    public void setBooksAutor(List<Book> booksAutor) {
        this.booksAutor = booksAutor;
    }
}
