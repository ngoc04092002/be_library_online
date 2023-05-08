package ltw.btl.repository.books;

import ltw.btl.model.Book.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepo extends JpaRepository<BookEntity, Long> {
    BookEntity getById(Long id);

    BookEntity getByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title,String author);
    void deleteById(Long id);
}
