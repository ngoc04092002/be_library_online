package ltw.btl.service.report.booksSold;

import lombok.RequiredArgsConstructor;
import ltw.btl.model.Report.BooksSoldEntity;
import ltw.btl.repository.report.IBooksSoldRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksSoldService implements IBooksSoldService {
    private final IBooksSoldRepo iBooksSoldRepo;

    @Override
    public BooksSoldEntity booksSoldInfo(BooksSoldEntity booksSoldEntity) {
        final var currentBookSoldYear = iBooksSoldRepo.getByYear(booksSoldEntity.getYear());
        final var currentBookSoldMonth = iBooksSoldRepo.getByMonth(booksSoldEntity.getMonth());

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        Long currentSold = booksSoldEntity.getSolds();

        if (currentBookSoldYear == null && currentBookSoldMonth == null) {
            BooksSoldEntity newBooksSoldEntity = new BooksSoldEntity();
            newBooksSoldEntity.setSolds(currentSold);
            newBooksSoldEntity.setYear(year);
            newBooksSoldEntity.setMonth(month);
            return iBooksSoldRepo.save(newBooksSoldEntity);
        }
        if (currentBookSoldYear == null) {
            currentBookSoldMonth.setSolds(currentSold);
            currentBookSoldMonth.setYear(year);
        } else {
            currentBookSoldMonth.setSolds(currentBookSoldMonth.getSolds() + currentSold);
        }

        return iBooksSoldRepo.save(currentBookSoldMonth);
    }

    @Override
    public List<BooksSoldEntity> getBooksSoldInfo() {
        return iBooksSoldRepo.findAll();
    }
}
