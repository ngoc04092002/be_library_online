package ltw.btl.controller.auth;


import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ltw.btl.model.Client.EWaitingR;
import ltw.btl.service.email.EmailService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/", produces = "application/json")
@RequiredArgsConstructor
public class ForgotPassword {

    private final EmailService emailService;

    @GetMapping("forgot-password")
    public String sendMailForgotPassword(@RequestParam String email) throws MessagingException {
        return emailService.sendEmail(email);
    }

    @GetMapping("reset-password")
    public String resetPassword(@RequestParam String e) {
        return emailService.resetPassword(e);
    }

    @GetMapping("get-all-ewaitingr")
    public List<EWaitingR> getAllEWaitingR() {
        return emailService.getAllEWaitingR();
    }

    @PostMapping("delete-forgotpass-ids")
    public Boolean deleteFeedbackIds(@RequestBody List<Long> ids){
        try{
            emailService.deleteUserEmailWithIds(ids);
            return true;
        }catch (Exception e){
            return false;
        }

    }

}
