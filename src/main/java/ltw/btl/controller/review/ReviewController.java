package ltw.btl.controller.review;

import lombok.RequiredArgsConstructor;
import ltw.btl.model.review.ReviewEntity;
import ltw.btl.service.review.IReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ReviewController {
    private final IReviewService iReviewService;

    @GetMapping("reviews")
    public List<ReviewEntity> getAllReviews(){
        return iReviewService.getAllReviews();
    }

    @PostMapping("save-review")
    public ReviewEntity createReivew(@RequestBody ReviewEntity reviewEntity){
        return iReviewService.createReview(reviewEntity);
    }

    @DeleteMapping("remove-review/{id}")
    public Boolean removeReivew(@PathVariable Long id){
        return iReviewService.removeReview(id);
    }
}
