package LibraryManagement.LMS.borrowedBooks;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BorrowRecordRepository
        extends JpaRepository<BorrowRecord, Long> {

    Optional<BorrowRecord>
    findTopByBookIdAndReturnedFalseOrderByBorrowDateDesc(Long bookId);
}
