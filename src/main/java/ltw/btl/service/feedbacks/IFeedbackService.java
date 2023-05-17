package ltw.btl.service.feedbacks;

import ltw.btl.model.Feedbacks.FeedbackEntity;

import java.util.List;

public interface IFeedbackService {
    Boolean saveFeedback(FeedbackEntity feedbackEntity);

    List<FeedbackEntity> getAllFeedback();

    void deleteFeedbacksWithIds(List<Long> ids);
}
