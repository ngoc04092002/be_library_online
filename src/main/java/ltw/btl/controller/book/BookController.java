package ltw.btl.controller.book;

import lombok.RequiredArgsConstructor;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.service.book.IBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BookController {

    private final IBookService iBookService;

    @GetMapping("books")
    public List<BookEntity> getAllBooks() {
        return iBookService.getAllBooks();
    }

    @GetMapping("book/{id}")
    public BookEntity getBookById(@PathVariable Long id) {
        return iBookService.getBookById(id);
    }

    @DeleteMapping("book/{id}")
    public Boolean deleteBookById(@PathVariable Long id) {
        return iBookService.deleteBookById(id);
    }

    @PostMapping("add-book")
    public String addBook(@RequestBody BookEntity bookEntity) {
        System.out.println(
                bookEntity.getQuantitySold() + "-" + bookEntity.getReleaseDate() + "-" + bookEntity.getPages());
        return iBookService.createBook(bookEntity);
    }
}
