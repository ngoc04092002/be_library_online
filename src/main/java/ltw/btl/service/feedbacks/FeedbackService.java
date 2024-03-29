package ltw.btl.service.feedbacks;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ltw.btl.model.Feedbacks.FeedbackEntity;
import ltw.btl.repository.feedbacks.IFeedbackRepo;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedbackService implements IFeedbackService {

    private final IFeedbackRepo iFeedbackRepo;
    @Override
    public Boolean saveFeedback(FeedbackEntity feedbackEntity) {
        iFeedbackRepo.save(feedbackEntity);
        return true;
    }

    @Override
    public List<FeedbackEntity> getAllFeedback() {
        return iFeedbackRepo.findAll();
    }

    @Override
    public void deleteFeedbacksWithIds(List<Long> ids) {
        iFeedbackRepo.deleteFeedbacksWithIds(ids);
    }
}
