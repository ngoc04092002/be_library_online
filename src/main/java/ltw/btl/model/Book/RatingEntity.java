package ltw.btl.model.Book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "rating")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class RatingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "one" , columnDefinition = "int default 0")
    private Integer one;

    @Column(name = "two",columnDefinition = "int default 0")
    private Integer two;

    @Column(name = "three",columnDefinition = "int default 0")
    private Integer three;

    @Column(name = "four",columnDefinition = "int default 0")
    private Integer four;

    @Column(name = "five",columnDefinition = "int default 0")
    private Integer five;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_book_rating_id", referencedColumnName = "id")
    private BookEntity bookRating;
}
