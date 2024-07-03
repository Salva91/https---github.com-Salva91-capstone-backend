package it.epicode.santuario.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Component

public class RegisterUserDTO {
    String firstName;
    String lastName;
    String username;
    String email;
    String password;
    String avatar;
}