package ltw.btl.controller.report;

import lombok.RequiredArgsConstructor;
import ltw.btl.model.Report.BooksSoldEntity;
import ltw.btl.service.report.booksSold.IBooksSoldService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BooksSoldContainer {
    private final IBooksSoldService iBooksSoldService;

    @GetMapping("get-books_sold-info")
    public List<BooksSoldEntity> getBooksSold(){
        return iBooksSoldService.getBooksSoldInfo();
    }


    @PostMapping("update-books_sold")
    public BooksSoldEntity booksSoldInfo(@RequestBody BooksSoldEntity booksSoldEntity){
        return iBooksSoldService.booksSoldInfo(booksSoldEntity);
    }
}
