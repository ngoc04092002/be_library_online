package ltw.btl.repository.books;

import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.Book.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRatingRepo extends JpaRepository<RatingEntity,Long> {
    RatingEntity findByBookRating(BookEntity bookEntity);

    RatingEntity findByBookRating_Id(Long id);
}
