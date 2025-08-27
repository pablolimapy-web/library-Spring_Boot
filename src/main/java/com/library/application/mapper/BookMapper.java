package com.library.application.mapper;

import com.library.domain.model.Book;
import com.library.application.dto.BookDTO;

public class BookMapper {

    public static BookDTO toDTO(Book book) {
        if (book == null) return null;
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable());
    }

    public static Book toEntity(BookDTO dto) {
        if (dto == null) return null;
        return new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.isAvailable());
    }
}
