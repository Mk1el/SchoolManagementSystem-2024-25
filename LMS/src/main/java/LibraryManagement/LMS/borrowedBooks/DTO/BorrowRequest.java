package LibraryManagement.LMS.borrowedBooks.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowRequest {
    @NotNull
    private Long bookId;
    @NotNull
    private String borrowerName;
    private LocalDate borrowDate;
}
