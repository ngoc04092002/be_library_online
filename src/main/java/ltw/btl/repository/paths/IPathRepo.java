package ltw.btl.repository.paths;

import ltw.btl.model.paths.PathsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPathRepo extends JpaRepository<PathsEntity,Long> {
}
