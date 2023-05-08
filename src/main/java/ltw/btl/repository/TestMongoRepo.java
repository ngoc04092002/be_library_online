package ltw.btl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ltw.btl.model.TestMongo;

@Repository
public interface TestMongoRepo extends MongoRepository<TestMongo, String> {
}
