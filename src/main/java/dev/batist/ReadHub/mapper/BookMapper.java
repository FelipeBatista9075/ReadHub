package dev.batist.ReadHub.mapper;

import dev.batist.ReadHub.Entity.Book;
import dev.batist.ReadHub.request.BookRequest;
import dev.batist.ReadHub.response.BookResponse;

public class BookMapper {

    public static Book toBook(BookRequest bookRequest){
        return Book.builder()
                .id(bookRequest.getId())
                .titulo(bookRequest.getTitulo())
                .descricao(bookRequest.getDescricao())
                .authors(bookRequest.getAuthors())
                .ISBN(bookRequest.getISBN())
                .publicacao(bookRequest.getPublicacao())
                .numberPages(bookRequest.getNumberPages())
                .formato(bookRequest.getFormato())
                .editora(bookRequest.getEditora())
                .img(bookRequest.getImg())
                .build();
    }

    public static BookResponse toBookResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .titulo(book.getTitulo())
                .descricao(book.getDescricao())
                .authors(book.getAuthors())
                .ISBN(book.getISBN())
                .publicacao(book.getPublicacao())
                .numberPages(book.getNumberPages())
                .formato(book.getFormato())
                .editora(book.getEditora())
                .img(book.getImg())
                .build();
    }
}
