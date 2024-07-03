package it.epicode.santuario.security;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginResponseDTO {
    RegisteredUserDTO user;
    String token;
    public LoginResponseDTO() {
        // Costruttore vuoto necessario per l'iniezione da Spring
    }

    @Builder(setterPrefix = "with")
    public LoginResponseDTO(RegisteredUserDTO user, String token) {
        this.user = user;
        this.token = token;
    }
}

