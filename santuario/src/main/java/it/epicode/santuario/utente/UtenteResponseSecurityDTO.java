package it.epicode.santuario.utente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.santuario.animale.AnimaleResponseDTO;
import it.epicode.santuario.domicilio.Domicilio;
import it.epicode.santuario.security.Roles;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;
@Data
public class UtenteResponseSecurityDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String recapitoTelefonico;
    private String fotoUser;
    private String username;
    private String email;
    private String password;

    private List<Roles> roles;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;
   // @JsonIgnoreProperties("proprietario")
   // private List<AnimaleResponseDTO> animaliGestiti;
   // private String username; // Aggiungi il campo username
}
