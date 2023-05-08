package ltw.btl.repository.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ltw.btl.model.client.ClientEntity;

@Repository
public interface IClientRepo extends JpaRepository<ClientEntity, Long> {
    ClientEntity findByEmail(String email);

    ClientEntity findByUsernameOrEmail(String username,String email);


}