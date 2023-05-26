package ltw.btl.model.paths;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paths")
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class PathsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="path")
    private String path;
}

