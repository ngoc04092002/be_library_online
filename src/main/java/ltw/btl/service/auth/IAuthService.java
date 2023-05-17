package ltw.btl.service.auth;

import ltw.btl.dto.auth.UserResponse;
import ltw.btl.dto.clients.ClientResponse;
import ltw.btl.model.Client.ClientEntity;

public interface IAuthService {

    ClientResponse saveUser(UserResponse userResponse);

    ClientEntity fetchUser(String email);

    ClientEntity fetchUserSignup(String username, String email);

}
