package LibraryManagement.LMS.books;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Book getById(Long id);
    Book save(Book book);
    Book update(Long id, Book book);
    void delete(Long id);
    Page<Book> getBooks(String search, String sortBy, String order, int page, int size);
}

