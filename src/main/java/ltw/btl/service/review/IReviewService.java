package ltw.btl.service.review;

import ltw.btl.model.review.ReviewEntity;

import java.util.List;

public interface IReviewService {
    List<ReviewEntity> getAllReviews();

    ReviewEntity createReview(ReviewEntity reviewEntity);

    Boolean removeReview(Long id);
}
