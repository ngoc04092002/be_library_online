package ltw.btl.dto.clients;

import ltw.btl.model.Client.ClientEntity;


public record ClientResponse(Long id, String username, String email, String address, String gender, String avatar,
                             String role) {
    public ClientResponse(ClientEntity clientEntity) {
        this(clientEntity.getId(), clientEntity.getUsername(), clientEntity.getEmail(), clientEntity.getAddress(),
             clientEntity.getGender(), clientEntity.getAvatar(), clientEntity.getRole());
    }
}