package ltw.btl.controller.clients;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ltw.btl.dto.clients.ClientResponse;
import ltw.btl.dto.clients.PasswordRequest;
import ltw.btl.dto.clients.UpdateClientRequest;
import ltw.btl.model.Client.ClientEntity;
import ltw.btl.service.clients.IClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ClientController {

    private final IClientService iClientService;

    @GetMapping("getAll-clients")
    public List<ClientResponse> getAllCLient(){
        List<ClientEntity> allClient = iClientService.getAllCLients();

        return allClient.stream().map(this::clientResponse).toList();
    }

    @PostMapping("save-client")
    public ClientResponse saveClient(@RequestBody ClientEntity clientEntity){
        return iClientService.saveClient(clientEntity);
    }

    @PutMapping("update-password")
    public String updatePassword(@RequestBody @Valid PasswordRequest passwordRequest){
        return iClientService.updatePassword(passwordRequest);
    }

    @PutMapping("update-client-info")
    public ClientResponse updatePassword(@RequestBody @Valid UpdateClientRequest updateClientRequest,
            @RequestParam String oldEmail){
        return iClientService.updateClient(updateClientRequest, oldEmail);
    }

    @DeleteMapping("/delete-client/{id}")
    public Boolean deleteClient(@PathVariable("id") Long id){
        return iClientService.deleteClient(id);
    }

    private ClientResponse clientResponse(ClientEntity clientEntity){
        return new ClientResponse(clientEntity);
    }
}
