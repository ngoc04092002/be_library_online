package ltw.btl.model.Report;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "books_sold")
@NoArgsConstructor
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BooksSoldEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "solds")
    private Integer solds;
}
