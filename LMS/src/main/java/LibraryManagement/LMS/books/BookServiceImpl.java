package LibraryManagement.LMS.books;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;


    @Override
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    @Override
    public Book getById(Long id){
        return bookRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Book not found with ID: " + id));
    }
    @Override
    public Book save(Book book){
        return bookRepository.save(book);
    }
    @Override
    public Book update(Long id, Book book) {
        Book existing = getById(id);
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setIsbn(book.getIsbn());
        existing.setQuantity(book.getQuantity());
        return bookRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        Book book = getById(id);
        bookRepository.delete(book);
    }
    @Override
    public Page<Book> getBooks(String search, String sortBy, String order, int page, int size) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        if (search != null && !search.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(search, pageable);
        } else {
            return bookRepository.findAll(pageable);
        }
    }
}
