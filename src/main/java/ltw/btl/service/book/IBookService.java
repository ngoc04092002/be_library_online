package ltw.btl.service.book;

import ltw.btl.model.Book.BookEntity;

import java.awt.print.Book;
import java.text.ParseException;
import java.util.List;

public interface IBookService {
    List<BookEntity> getAllBooks();

    BookEntity getBookById(Long id);

    Boolean deleteBookById(Long id);

    String createBook(BookEntity bookEntity);

    String updateBook(BookEntity bookEntity);

    List<BookEntity> filterBooks(String s, Integer limit, Integer offset);
}
