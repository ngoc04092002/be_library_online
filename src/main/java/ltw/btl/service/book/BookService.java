package ltw.btl.service.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.repository.books.IBookRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService implements IBookService {

    private final IBookRepo iBookRepo;

    @Override
    public List<BookEntity> getAllBooks() {
        return iBookRepo.findAll();
    }

    @Override
    public BookEntity getBookById(Long id) {
        return iBookRepo.getById(id);
    }

    @Override
    public Boolean deleteBookById(Long id) {
        try {
            iBookRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String createBook(BookEntity bookEntity) {

        try {
            BookEntity findBook = iBookRepo.getByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                    bookEntity.getTitle(), bookEntity.getAuthor());

            if (findBook != null) {
                return "Books already exist";
            }
            Date date1 = bookEntity.getReleaseDate();
            Date date = new Date();
            if (date1.after(date)) {
                return "Thời gian đã vượt qua thời điểm hiện tại";
            }
            iBookRepo.save(bookEntity);
            return "ok";
        } catch (Exception e) {
            return "Kiểm tra lại dữ liệu đã điền đẩy đủ chưa";
        }

    }

    @Override
    public String updateBook(BookEntity bookEntity) {
        try {
            String title = bookEntity.getTitle();
            String author = bookEntity.getAuthor();
            System.out.println(title + "-" + author);
            final var findBook = iBookRepo.getByTitleOrAuthor(title, author);
            System.out.println("2");
            if (findBook != null && findBook.getId() != bookEntity.getId()) {
                return "Books already exist";
            }

            final var newBook = BookEntity.builder()
                    .id(bookEntity.getId())
                    .author(bookEntity.getAuthor())
                    .des(bookEntity.getDes())
                    .pages(bookEntity.getPages())
                    .quantitySold(bookEntity.getQuantitySold())
                    .releaseDate(bookEntity.getReleaseDate())
                    .src(bookEntity.getSrc())
                    .title(bookEntity.getTitle())
                    .type(bookEntity.getType())
                    .build();

            Date date1 = bookEntity.getReleaseDate();
            Date date = new Date();


            if (date1.after(date)) {
                return "Thời gian đã vượt qua thời điểm hiện tại";
            }
            iBookRepo.save(newBook);
            return "ok";
        } catch (Exception e) {
            return "Kiểm tra lại dữ liệu đã điền đẩy đủ chưa";
        }
    }


    @Override
    public List<BookEntity> filterBooks(String s, Integer limit, Integer offset) {
        return iBookRepo.filterBooks(s,limit,offset);
    }
}
