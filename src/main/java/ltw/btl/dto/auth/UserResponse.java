package ltw.btl.dto.auth;

import ltw.btl.model.client.ClientEntity;

public record UserResponse(String username, String email, String address, String gender, String avatar, String password,
                           String role) {
    public UserResponse(ClientEntity clientEntity, String password) {
        this(clientEntity.getUsername(), clientEntity.getEmail(), clientEntity.getAddress(), clientEntity.getGender(),
             clientEntity.getAvatar(), password, clientEntity.getRole());
    }
}

