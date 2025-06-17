package LibraryManagement.LMS.borrowedBooks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="borrow_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class borrowedbooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private String borrowerame;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
