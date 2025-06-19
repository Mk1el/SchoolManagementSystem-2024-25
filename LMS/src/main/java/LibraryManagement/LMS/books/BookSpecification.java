package LibraryManagement.LMS.books;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> searchByTitleOrAuthor(String keyword){
        return (root, query, cb) -> {
            String pattern = "%" + keyword.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("title")), pattern),
                    cb.like(cb.lower(root.get("author")), pattern)
            );
        };
    }
}
