package LibraryManagement.LMS.borrowedBooks;

import LibraryManagement.LMS.borrowedBooks.DTO.BorrowRequest;
import LibraryManagement.LMS.borrowedBooks.DTO.ReturnRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@Valid @RequestBody BorrowRequest request) {
        borrowService.borrow(request);
        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@Valid @RequestBody ReturnRequest request) {
        borrowService.returnBook(request);
        return ResponseEntity.ok("Book returned successfully");
    }

}
