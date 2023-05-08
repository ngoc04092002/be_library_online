package ltw.btl.service;

import lombok.RequiredArgsConstructor;
import ltw.btl.repository.TestHibernateRepo;
import org.springframework.stereotype.Service;
import ltw.btl.dto.TestHibernateResponse;
import ltw.btl.model.TestHibernateEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestHibernateService implements ITestHibernateService{
    private final TestHibernateRepo testHibernateRepol;
    @Override
    public List<TestHibernateResponse> getE() {
        List<TestHibernateEntity> hibernateEntities = testHibernateRepol.findAll();

        return hibernateEntities.stream().map(this::mapToTestHibernateResponse).toList();
    }

    private TestHibernateResponse mapToTestHibernateResponse(TestHibernateEntity hibernateEntity) {
        return new TestHibernateResponse(hibernateEntity);
    }
}
