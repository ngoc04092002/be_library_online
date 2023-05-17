package ltw.btl.controller.book;

import lombok.RequiredArgsConstructor;
import ltw.btl.dto.book.RatingRequest;
import ltw.btl.dto.book.RatingResponse;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.Book.RatingEntity;
import ltw.btl.service.book.IBookService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BookController {

    private final IBookService iBookService;

    @GetMapping("books")
    public List<BookEntity> getAllBooks(@RequestParam(defaultValue = "") String type,
                                        @RequestParam(defaultValue = "") String year) {
        List<BookEntity> bookEntities = iBookService.getAllBooks();
        if (StringUtils.isNotBlank(type)) {
            bookEntities = bookEntities.stream()
                    .filter(b -> b.getType()
                            .equalsIgnoreCase(type))
                    .toList();
        }
        if (StringUtils.isNotBlank(year)) {
            bookEntities = bookEntities.stream()
                    .filter(b -> {

                        System.out.println(b.getReleaseDate()
                                .getYear() + "-" + b.getReleaseDate());
                        return b.getReleaseDate()
                                .getYear() + 1900 == Integer.parseInt(year);
                    })
                    .toList();
        }
        return bookEntities;
    }

    @GetMapping("book/{id}")
    public BookEntity getBookById(@PathVariable Long id) {
        return iBookService.getBookById(id);
    }

    @GetMapping("filter-book")
    public List<BookEntity> filterBooks(@RequestParam(defaultValue = "") String s,
                                        @RequestParam(defaultValue = "10") Integer limit, @RequestParam(defaultValue = "0") Integer offset) {
        return iBookService.filterBooks(s, limit, offset);
    }

    @DeleteMapping("book/{id}")
    public Boolean deleteBookById(@PathVariable Long id) {
        return iBookService.deleteBookById(id);
    }

    @PostMapping("save-rating")
    public RatingEntity saveRating(@RequestBody RatingRequest bookEntity) {
        return iBookService.saveRating(bookEntity.getBookEntity(), bookEntity.getStar());
    }

    @GetMapping("get-rating")
    public RatingResponse getRating(@RequestParam Long id) {
        return iBookService.getRatings(id);
    }


    @PostMapping("add-book")
    public String addBook(@RequestBody BookEntity bookEntity) {
        return iBookService.createBook(bookEntity);
    }

    @PostMapping("update-book")
    public String updateBook(@RequestBody BookEntity bookEntity) {
        return iBookService.updateBook(bookEntity);
    }

}
