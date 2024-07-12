package it.epicode.santuario.security;

import it.epicode.santuario.domicilio.Domicilio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Component

public class RegisterUserDTO {
    String nome;
    String cognome;
    String username;
    String email;
    String password;
    String avatar;
    Domicilio domicilio;

    public RegisterUserDTO(String nome, String cognome, String username, String email, String password, String avatar) {
    }
}