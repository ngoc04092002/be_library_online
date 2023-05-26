package ltw.btl.controller.report;

import lombok.RequiredArgsConstructor;
import ltw.btl.dto.report.BookAndFeedbackReportResponse;
import ltw.btl.service.report.BookAndFeedback.BFService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BFController {

    private final BFService bfService;
    @GetMapping("get-book-feedback-report")
    public BookAndFeedbackReportResponse getBookAndFeedbackReport(){
        return bfService.getBookAndFeedbackReport();
    }
}
