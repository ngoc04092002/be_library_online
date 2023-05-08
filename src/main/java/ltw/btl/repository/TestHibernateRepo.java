package ltw.btl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ltw.btl.model.TestHibernateEntity;

@Repository
public interface TestHibernateRepo extends JpaRepository<TestHibernateEntity, Long> {
}
