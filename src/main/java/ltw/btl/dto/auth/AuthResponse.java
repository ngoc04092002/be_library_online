package ltw.btl.dto.auth;

import ltw.btl.model.Client.ClientEntity;

public record AuthResponse(Long id, String username, String email, String address, String gender, String avatar,
                          String role, String token) {
    public AuthResponse(ClientEntity clientEntity, String token) {
        this(clientEntity.getId(), clientEntity.getUsername(), clientEntity.getEmail(), clientEntity.getAddress(),
             clientEntity.getGender(), clientEntity.getAvatar(),  clientEntity.getRole(), token);
    }
}
