package ltw.btl.model.Orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ltw.btl.model.Book.BookEntity;

import java.io.Serializable;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class OrderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="tel",nullable = false)
    private String tel;

    @Column(name="status",nullable = false)
    private Boolean status;
    @Column(name="quantity",columnDefinition = "int default 0")
    private Integer quantity;

    @Column(name="address",nullable = false)
    private String address;

    @OneToOne
    private BookEntity books;

}
