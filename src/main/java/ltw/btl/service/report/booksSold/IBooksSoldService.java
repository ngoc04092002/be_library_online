package ltw.btl.service.report.booksSold;

import ltw.btl.model.Report.BooksSoldEntity;

import java.util.List;

public interface IBooksSoldService {

    BooksSoldEntity booksSoldInfo(BooksSoldEntity booksSoldEntity);

    List<BooksSoldEntity> getBooksSoldInfo();
}
