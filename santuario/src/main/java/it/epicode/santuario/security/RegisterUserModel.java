package it.epicode.santuario.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserModel {

        @NotBlank(message = "Il tuo nome non può essere vuoto")
        private String firstName;

        @NotBlank(message = "Il tuo cognome non può essere vuoto")
        private String lastName;

        @NotBlank(message = "Lo username non può contenere solo spazi vuoti")
        @Size(max = 50, message = "Il tuo username è troppo lungo, max 50 caratteri")
        private String username;

        @Email(message = "Inserisci una email valida")
        private String email;

        @NotBlank(message = "La password non può contenere solo spazi vuoti")
        @Size(max = 125, message = "La password è troppo lunga, max 125 caratteri")
        private String password;

}
