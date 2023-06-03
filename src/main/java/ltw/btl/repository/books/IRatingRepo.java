package ltw.btl.repository.books;

import ltw.btl.dto.book.RatingResponse;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.Book.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRatingRepo extends JpaRepository<RatingEntity,Long> {
    RatingEntity findByBookRating(BookEntity bookEntity);

    @Modifying
    @Query(value = "SELECT gr.star, gr.amount " +
            "FROM (SELECT r.id,star, COUNT(r.star) AS amount, fk_client_rating_id " +
            "            FROM rating r " +
            "            WHERE r.fk_book_rating_id = :bookId " +
            "            GROUP BY r.star, r.fk_client_rating_id " +
            "            ORDER BY COUNT(r.star) DESC, r.star DESC) gr " +
            "GROUP BY gr.fk_client_rating_id", nativeQuery = true)
    List<Object[]> findByBookRatingId(@Param("bookId") Long bookId);
}
