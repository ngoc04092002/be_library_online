package ltw.btl.repository.books;

import ltw.btl.dto.book.RatingResponse;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.Book.RatingEntity;
import ltw.btl.model.Client.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRatingRepo extends JpaRepository<RatingEntity,Long> {
    RatingEntity findByBookRatingAndClientEntity(BookEntity bookEntity, ClientEntity clientEntity);

    @Modifying
    @Query(value = "SELECT star, count(fk_client_rating_id) AS amount FROM rating r WHERE r.fk_book_rating_id =:bookId GROUP BY r.star", nativeQuery = true)
    List<Object[]> findByBookRatingId(@Param("bookId") Long bookId);
}
