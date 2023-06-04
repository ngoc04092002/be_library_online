package ltw.btl.service.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ltw.btl.dto.book.BookRequest;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.Book.RatingEntity;
import ltw.btl.repository.books.IBookRepo;
import ltw.btl.repository.books.IOrderRepo;
import ltw.btl.repository.books.IRatingRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService implements IBookService {

    private final IBookRepo iBookRepo;
    private final IRatingRepo iRatingRepo;

    private final IOrderRepo iOrderRepo;

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
            BookEntity findBook = iBookRepo.getByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(
                    bookEntity.getTitle(), bookEntity.getAuthor());

            if (findBook != null) {
                return "Sách này đã hiện có";
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
    public String updateBook(BookRequest updateBook) {
        System.out.println(updateBook.getId());

        try {
            String title = updateBook.getTitle();
            String author = updateBook.getAuthor();
            final var findBook = iBookRepo.getByTitleOrAuthor(title, author);
            if (findBook != null && !updateBook.getTitle()
                    .equalsIgnoreCase(updateBook.getOldTitle()) && !updateBook.getAuthor()
                    .equalsIgnoreCase(updateBook.getOldAuthor())) {
                return "Sách đã tồn tại";
            }
            final var bookEntity = BookEntity.builder()
                    .id(updateBook.getId())
                    .author(updateBook.getAuthor())
                    .des(updateBook.getDes())
                    .pages(updateBook.getPages())
                    .quantitySold(updateBook.getQuantitySold())
                    .releaseDate(updateBook.getReleaseDate())
                    .src(updateBook.getSrc())
                    .title(updateBook.getTitle())
                    .price(updateBook.getPrice())
                    .type(updateBook.getType())
                    .build();

            Date date1 = updateBook.getReleaseDate();
            Date date = new Date();


            if (date1.after(date)) {
                return "Thời gian đã vượt qua thời điểm hiện tại";
            }
            iBookRepo.save(bookEntity);
            return "ok";
        } catch (Exception e) {
            System.out.println("error update book" + e.getMessage());
            return "Kiểm tra lại dữ liệu đã điền đẩy đủ chưa";
        }
    }


    @Override
    public List<BookEntity> filterBooks(String s, Integer limit, Integer offset) {
        return iBookRepo.filterBooks(s, limit, offset);
    }

    @Override
    public RatingEntity saveRating(RatingEntity rating) {
        final var existOrder = iOrderRepo.getByNameContainingIgnoreCaseAndStatusAndBooks_Id(rating.getClientEntity()
                                                                                                    .getUsername(), 2,
                                                                                            rating.getBookRating()
                                                                                                    .getId());
        if (existOrder != null) {
            final var currentReview = iRatingRepo.findByBookRatingAndClientEntity(rating.getBookRating(),
                                                                                  rating.getClientEntity());
            if(currentReview!=null){
                currentReview.setStar(rating.getStar());
                return iRatingRepo.save(currentReview);
            }
            return iRatingRepo.save(rating);
        }

        return new RatingEntity();
    }

    @Override
    public List<Object[]> getRatings(Long id) {
        return iRatingRepo.findByBookRatingId(id);
    }

}
