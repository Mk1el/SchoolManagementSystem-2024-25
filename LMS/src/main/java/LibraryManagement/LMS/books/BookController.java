package LibraryManagement.LMS.books;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping("/all")
    public List<Book> getAllBooks(){
        return bookService.getAll();
    }
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        return bookService.getById(id);
    }
    @PostMapping
    public Book addBook(@Valid @RequestBody Book book){
        return bookService.save(book);
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book book){
        return bookService.update(id, book);
    }
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.delete(id);
    }
    @GetMapping
    public Page<Book> getBooks(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return bookService.getBooks(search, sortBy, order, page, size);
    }


}
