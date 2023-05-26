package ltw.btl.repository.books;

import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.Feedbacks.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepo extends JpaRepository<BookEntity, Long> {
    BookEntity getById(Long id);

    BookEntity getByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);

    void deleteById(Long id);

    BookEntity getByTitleOrAuthor(String title, String author);

    @Modifying
    @Query(nativeQuery = true, value = "select * from books where month(created_at)=?1 and year(created_at)=?2")
    List<BookEntity> getBookReport(Integer month, Integer year);
    @Modifying
    @Query(nativeQuery = true, value = "select * from books where title like CONCAT('%',:s,'%') or author like CONCAT('%',:s,'%') limit " + ":limit offset :offset")
    List<BookEntity> filterBooks(@Param("s") String s, @Param("limit") Integer limit, @Param("offset") Integer offset);
}
