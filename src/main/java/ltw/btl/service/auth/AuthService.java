package ltw.btl.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ltw.btl.dto.auth.UserResponse;
import ltw.btl.dto.clients.ClientResponse;
import ltw.btl.model.Client.ClientEntity;
import ltw.btl.repository.clients.IClientRepo;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final IClientRepo iClientRepo;

    @Override
    public ClientResponse saveUser(UserResponse userResponse) {
        ClientEntity clientEntity=new ClientEntity(userResponse);
        ClientEntity clientEntity1 = iClientRepo.save(clientEntity);
        return new ClientResponse(clientEntity1);
    }

    @Override
    public ClientEntity fetchUser(String email) {
        return iClientRepo.findByEmail(email);
    }

    @Override
    public ClientEntity fetchUserSignup(String username, String email) {
        return iClientRepo.findByUsernameOrEmail(username,email);
    }


}
