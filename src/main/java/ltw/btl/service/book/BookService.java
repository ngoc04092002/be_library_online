package ltw.btl.service.book;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ltw.btl.dto.book.RatingResponse;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.Book.RatingEntity;
import ltw.btl.repository.books.IBookRepo;
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
        return iBookRepo.filterBooks(s, limit, offset);
    }

    @Override
    public RatingEntity saveRating(BookEntity bookEntity, Integer star) {
        System.out.println(bookEntity.getId());
        RatingEntity rating = iRatingRepo.findByBookRating(bookEntity);
        if (rating != null) {
            checkRating(star, rating);
        } else {
            rating = new RatingEntity();
            checkRating(star, rating);
        }
        rating.setBookRating(bookEntity);


        return iRatingRepo.save(rating);
    }

    @Override
    public RatingResponse getRatings(Long id) {
        final var rating = iRatingRepo.findByBookRating_Id(id);
        if(rating==null){
            return new RatingResponse(0,0,0,0,0);
        }
        return new RatingResponse(rating);
    }

    private void checkRating(Integer star, RatingEntity rating) {
        switch (star) {
            case 5 -> rating.setFive(rating.getFive() == null ? 1 : rating.getFive() + 1);
            case 4 -> rating.setFour(rating.getFour() == null ? 1 : rating.getFour() + 1);
            case 3 -> rating.setThree(rating.getThree() == null ? 1 : rating.getThree() + 1);
            case 2 -> rating.setTwo(rating.getTwo() == null ? 1 : rating.getTwo() + 1);
            default -> rating.setOne(rating.getOne() == null ? 1 : rating.getOne() + 1);
        }
    }
}
