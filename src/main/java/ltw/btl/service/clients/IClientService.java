package ltw.btl.service.clients;

import ltw.btl.dto.clients.ClientResponse;
import ltw.btl.dto.clients.PasswordRequest;
import ltw.btl.dto.clients.UpdateClientRequest;
import ltw.btl.model.Client.ClientEntity;

import java.util.List;

public interface IClientService {

    List<ClientEntity> getAllCLients();

    ClientResponse saveClient(ClientEntity clientEntity);

    String updatePassword(PasswordRequest passwordRequest);

    Boolean deleteClient(Long id);

    ClientResponse updateClient(UpdateClientRequest updateClientRequest, String oldEmail);
}
