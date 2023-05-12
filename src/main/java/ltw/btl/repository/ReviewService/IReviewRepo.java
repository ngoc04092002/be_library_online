package ltw.btl.repository.ReviewService;

import ltw.btl.model.review.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepo extends JpaRepository<ReviewEntity, Long> {
    void deleteById(Long id);
}
