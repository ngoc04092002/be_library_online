package ltw.btl.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.btl.model.Book.BookEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingRequest {
    private BookEntity bookEntity;
    private Integer star;
}
