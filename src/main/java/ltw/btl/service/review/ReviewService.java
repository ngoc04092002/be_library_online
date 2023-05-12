package ltw.btl.service.review;

import lombok.RequiredArgsConstructor;
import ltw.btl.model.review.ReviewEntity;
import ltw.btl.repository.ReviewService.IReviewRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService{
    private final IReviewRepo iReviewRepo;

    @Override
    public List<ReviewEntity> getAllReviews() {
        return iReviewRepo.findAll();
    }

    @Override
    public ReviewEntity createReview(ReviewEntity reviewEntity) {
        return iReviewRepo.save(reviewEntity);
    }

    @Override
    public Boolean removeReview(Long id) {
        try{
            iReviewRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            System.out.println("reviews=>>>"+ex.getMessage());
            return false;
        }
    }
}
