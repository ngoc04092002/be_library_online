package ltw.btl.model.Client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ltw.btl.dto.auth.AuthRequestSocial;
import ltw.btl.dto.auth.UserResponse;
import ltw.btl.model.Book.BookEntity;
import ltw.btl.model.review.ReviewEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "clients")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ClientEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ClientEntity(UserResponse userResponse) {
        this.username = userResponse.username();
        this.email = userResponse.email();
        this.address = userResponse.address();
        this.gender = userResponse.gender();
        this.password = userResponse.password();
        this.avatar = "";
        this.role = "user";
        this.createdAt = LocalDateTime.now();
    }

    public ClientEntity(AuthRequestSocial authRequestSocial, String encodedPassword) {
        this.username = authRequestSocial.getUsername();
        this.email = authRequestSocial.getEmail();
        this.address = "";
        this.gender = "male";
        this.password = encodedPassword;
        this.avatar = authRequestSocial.getAvatar();
        this.role = "user";
        this.createdAt = LocalDateTime.now();
    }

    @Column(name = "username", nullable = false, columnDefinition = "nvarchar(255)")
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "avatar", nullable = false, columnDefinition = "varchar(225) default ''")
    private String avatar;

    @Column(name = "password")
    private String password;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(225) default 'user'")
    private String role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @JsonBackReference(value = "client_review")
//    @JsonManagedReference(value = "client_review")
    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewList;

    @JsonBackReference(value = "client_book")
    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private List<BookEntity> bookEntities;
}
