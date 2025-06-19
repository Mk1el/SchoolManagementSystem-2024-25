package LibraryManagement.LMS.borrowedBooks;

import LibraryManagement.LMS.borrowedBooks.DTO.BorrowRequest;
import LibraryManagement.LMS.borrowedBooks.DTO.ReturnRequest;

public interface BorrowService {
    void borrow(BorrowRequest request);
    void returnBook(ReturnRequest request);
}
