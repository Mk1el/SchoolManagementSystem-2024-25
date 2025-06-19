package LibraryManagement.LMS.borrowedBooks.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnRequest {
    @NotNull
    private Long bookId;
    private LocalDate returnDate;
}
