package ltw.btl.service.report.booksSold;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ltw.btl.model.Report.BooksSoldEntity;
import ltw.btl.repository.books.IBookRepo;
import ltw.btl.repository.report.IBooksSoldRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BooksSoldService implements IBooksSoldService {
    private final IBooksSoldRepo iBooksSoldRepo;
    private final IBookRepo iBookRepo;

    @Override
    public BooksSoldEntity booksSoldInfo(BooksSoldEntity booksSoldEntity) {
        final var currentBookSoldMonth = iBooksSoldRepo.getByMonth(booksSoldEntity.getMonth());

        Date date = new Date();
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int currentSold = booksSoldEntity.getSolds();


        iBookRepo.updateSold(booksSoldEntity.getSolds(), booksSoldEntity.getBookId());

        if (currentBookSoldMonth == null) {
            BooksSoldEntity newBooksSoldEntity = new BooksSoldEntity();
            newBooksSoldEntity.setSolds(currentSold);
            newBooksSoldEntity.setYear(year);
            newBooksSoldEntity.setMonth(month);
            return iBooksSoldRepo.save(newBooksSoldEntity);
        }

        if (currentBookSoldMonth.getYear() != year) {
            currentBookSoldMonth.setYear(year);
        }

        currentBookSoldMonth.setSolds(currentSold + currentBookSoldMonth.getSolds());

        return iBooksSoldRepo.save(currentBookSoldMonth);
    }

    @Override
    public List<BooksSoldEntity> getBooksSoldInfo() {
        return iBooksSoldRepo.findAll();
    }
}
