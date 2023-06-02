package ltw.btl.dto.book;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.btl.model.Book.BookEntity;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequest {
    private Long id;
    private String title;
    private String author;
    private String type;
    private String des;
    private String src;
    private Date releaseDate;
    private Integer pages;
    private Integer quantitySold;
    private String oldTitle;
    private String oldAuthor;
}
