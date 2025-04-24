package dev.batist.ReadHub.mapper;

import dev.batist.ReadHub.Entity.Author;
import dev.batist.ReadHub.request.AuthorRequest;
import dev.batist.ReadHub.response.AuthorResponse;

public class AuthorMapper {

    public static Author toAuthor(AuthorRequest authorRequest){
        return Author.builder()
                .name(authorRequest.getName())
                .bio(authorRequest.getBio())
                .booksAutor(authorRequest.getBooksAutor())
                .build();
    }

    public static AuthorResponse toAuthorResponse(Author author){
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .bio(author.getBio())
                .booksAutor(author.getBooksAutor())
                .build();
    }
}
