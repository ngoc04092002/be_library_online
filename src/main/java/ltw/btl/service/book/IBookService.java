package ltw.btl.service.book;

import ltw.btl.dto.book.BookRequest;
import ltw.btl.dto.book.RatingResponse;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.Book.RatingEntity;

import java.util.List;

public interface IBookService {
    List<BookEntity> getAllBooks();

    BookEntity getBookById(Long id);

    Boolean deleteBookById(Long id);

    String createBook(BookEntity bookEntity);

    String updateBook(BookRequest newBook);

    List<BookEntity> filterBooks(String s, Integer limit, Integer offset);

    RatingEntity saveRating(RatingEntity rating);

    List<Object[]> getRatings(Long id);

}
