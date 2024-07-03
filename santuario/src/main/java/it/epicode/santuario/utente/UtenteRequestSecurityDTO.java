package it.epicode.santuario.utente;

import it.epicode.santuario.domicilio.Domicilio;
import it.epicode.santuario.security.Roles;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UtenteRequestSecurityDTO {
    @NotBlank(message = "Il tuo nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il tuo cognome non può essere vuoto")
    private String cognome;

    private String fotoUser;

    @NotBlank(message = "Lo username non può contenere solo spazi vuoti")
    @Size(max = 50, message = "Il tuo username è troppo lungo, max 50 caratteri")
    private String username;

    @Email(message = "Inserisci una email valida")
    private String email;

    @NotBlank(message = "La password non può contenere solo spazi vuoti")
    @Size(max = 125, message = "La password è troppo lunga, max 125 caratteri")
    private String password;

    private Domicilio domicilio;
    private String recapitoTelefonico;
    private List<Roles> roles;

    @Builder(setterPrefix = "with")
    public UtenteRequestSecurityDTO(String nome, String cognome, Domicilio domicilio, String recapitoTelefonico, String fotoUser, String username, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.fotoUser = fotoUser;
        this.email = email;
        this.password = password;
        this.domicilio = domicilio;
        this.recapitoTelefonico = recapitoTelefonico;
    }
}