package LibraryManagement.LMS.borrowedBooks;

import LibraryManagement.LMS.books.Book;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Only this mapping of the FK column—remove any other bookId mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    private String borrowerName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean returned;
}
