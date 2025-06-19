package LibraryManagement.LMS.borrowedBooks;

import LibraryManagement.LMS.books.Book;                        // uppercase B
import LibraryManagement.LMS.books.BookRepository;
import LibraryManagement.LMS.borrowedBooks.DTO.BorrowRequest;
import LibraryManagement.LMS.borrowedBooks.DTO.ReturnRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    @Override
    public void borrow(BorrowRequest request) {
        // 1. Load the book (or throw 404)
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book not found with ID " + request.getBookId()));

        // 2. Check quantity
        if (book.getQuantity() <= 0) {
            throw new IllegalStateException("Book is currently unavailable");
        }

        // 3. Decrement and save
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // 4. Create & save borrow record
        BorrowRecord record = new BorrowRecord();     // <-- correct class
        record.setBook(book);
        record.setBorrowerName(request.getBorrowerName());
        record.setBorrowDate(
                request.getBorrowDate() != null
                        ? request.getBorrowDate()
                        : LocalDate.now()
        );
        record.setReturned(false);
        borrowRecordRepository.save(record);
    }

    @Override
    public void returnBook(ReturnRequest request) {
        // 1. Load the book
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book not found with ID " + request.getBookId()));

        // 2. Find the latest non-returned borrow record
        BorrowRecord record = borrowRecordRepository
                .findTopByBookIdAndReturnedFalseOrderByBorrowDateDesc(request.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No active borrow record for book ID " + request.getBookId()));

        // 3. Mark it returned
        record.setReturnDate(
                request.getReturnDate() != null
                        ? request.getReturnDate()
                        : LocalDate.now()
        );
        record.setReturned(true);
        borrowRecordRepository.save(record);

        // 4. Increment book quantity
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }
}
