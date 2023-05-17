package ltw.btl.repository.report;

import ltw.btl.model.Report.BooksSoldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBooksSoldRepo extends JpaRepository<BooksSoldEntity,Long> {
    BooksSoldEntity getByMonth(int month);

    BooksSoldEntity getByYear(int year);
}
