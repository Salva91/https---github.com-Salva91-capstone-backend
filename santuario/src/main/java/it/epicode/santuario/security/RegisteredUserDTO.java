package it.epicode.santuario.security;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@Component
public class RegisteredUserDTO {
    Long id;
    String nome;
    String cognome;
    String username;
    String email;
    private List<Roles> roles;

    @Builder(setterPrefix = "with")
    public RegisteredUserDTO(Long id, String nome, String cognome, String username, String email, List<Roles> roles) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}