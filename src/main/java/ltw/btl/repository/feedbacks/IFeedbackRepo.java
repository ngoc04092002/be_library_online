package ltw.btl.repository.feedbacks;

import ltw.btl.model.Feedbacks.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedbackRepo extends JpaRepository<FeedbackEntity, Long> {
    @Modifying
    @Query("delete from FeedbackEntity u where u.id in ?1")
    void deleteFeedbacksWithIds(List<Long> ids);

    @Modifying
    @Query(nativeQuery = true, value = "select * from feedbacks where month(created_at)=?1 and year(created_at)=?2")
    List<FeedbackEntity> getFeedbackReport(Integer month, Integer year);
}
