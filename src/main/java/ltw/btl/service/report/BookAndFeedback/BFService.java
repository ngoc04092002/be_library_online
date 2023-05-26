package ltw.btl.service.report.BookAndFeedback;

import lombok.RequiredArgsConstructor;
import ltw.btl.dto.report.BFEntity;
import ltw.btl.dto.report.BookAndFeedbackReportResponse;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.Feedbacks.FeedbackEntity;
import ltw.btl.repository.books.IBookRepo;
import ltw.btl.repository.feedbacks.IFeedbackRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BFService {

    private final IBookRepo iBookRepo;
    private final IFeedbackRepo iFeedbackRepo;

    public BookAndFeedbackReportResponse getBookAndFeedbackReport() {
        Date date = new Date();
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();

        List<BookEntity> bookEntity = new ArrayList<>();
        List<FeedbackEntity> bfEntity = new ArrayList<>();
        BFEntity bookReport = new BFEntity(0, 0, true);
        BFEntity feedbackReport = new BFEntity(0, 0, true);

        var currentMonthBook = bookEntity;
        var lastMonthBook = bookEntity;
        var currentMonthFeedback = bfEntity;
        var lastMonthFeedback = bfEntity;

        if (month == 1) {
            currentMonthBook = iBookRepo.getBookReport(month, year);
            lastMonthBook = iBookRepo.getBookReport(12, year-1);
            currentMonthFeedback = iFeedbackRepo.getFeedbackReport(month, year);
            lastMonthFeedback = iFeedbackRepo.getFeedbackReport(12, year-1);
        } else {
            currentMonthBook = iBookRepo.getBookReport(month, year);
            lastMonthBook = iBookRepo.getBookReport(month - 1, year);
            currentMonthFeedback = iFeedbackRepo.getFeedbackReport(month, year);
            lastMonthFeedback = iFeedbackRepo.getFeedbackReport(month - 1, year);

        }

        float developSpeedBook = (currentMonthBook.size() - lastMonthBook.size()) / (lastMonthBook.size() == 0 ? currentMonthBook.size() == 0 ? 1 : currentMonthBook.size() : lastMonthBook.size());
        bookReport.setDevelopSpeed(Float.parseFloat(String.format("%.2f", Math.abs(developSpeedBook * 100))));
        bookReport.setIncrement(developSpeedBook < 0 ? false : true);
        bookReport.setSales(currentMonthBook.size());

        float developSpeedFeedback = (currentMonthFeedback.size() - lastMonthFeedback.size()) / (lastMonthFeedback.size() == 0 ? currentMonthFeedback.size() == 0 ? 1 : currentMonthFeedback.size() : lastMonthFeedback.size());
        feedbackReport.setDevelopSpeed(
                Float.parseFloat(String.format("%.2f", Math.abs(developSpeedFeedback * 100))));
        feedbackReport.setIncrement(developSpeedFeedback < 0 ? false : true);
        feedbackReport.setSales(currentMonthFeedback.size());
        return new BookAndFeedbackReportResponse(bookReport, feedbackReport);
    }
}
