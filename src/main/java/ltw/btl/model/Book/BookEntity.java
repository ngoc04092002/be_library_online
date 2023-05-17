package ltw.btl.model.Book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.btl.model.Client.ClientEntity;
import ltw.btl.model.review.ReviewEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "text", nullable = false)
    private String title;

    @Column(name = "author", columnDefinition = "nvarchar(256)", nullable = false)
    private String author;

    @Column(name = "type", columnDefinition = "nvarchar(256)",nullable = false)
    private String type;

    @Column(name = "des", columnDefinition = "text", nullable = false)
    private String des;

    @Column(name = "src", columnDefinition = "text",nullable = false)
    private String src;


    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @Column(name = "pages", columnDefinition = "int",nullable = false)
    private Integer pages;

    @Column(name = "quantity_sold", columnDefinition = "int default 0", nullable = false)
    private Integer quantitySold;

    @JsonManagedReference(value = "book_review")
    @OneToMany(mappedBy = "bookEntity", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewList;


    @JsonBackReference(value = "book_rating")
    @OneToMany(mappedBy = "bookRating", cascade = CascadeType.ALL)
    private List<RatingEntity> ratingEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_client_book_id", referencedColumnName = "id")
    private ClientEntity clientEntity;

}
