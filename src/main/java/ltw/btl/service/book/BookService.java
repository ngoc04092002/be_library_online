package ltw.btl.service.book;

import lombok.RequiredArgsConstructor;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.repository.books.IBookRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
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

            if(findBook!=null){
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
}
