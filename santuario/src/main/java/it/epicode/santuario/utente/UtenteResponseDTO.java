package it.epicode.santuario.utente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.epicode.santuario.animale.AnimaleResponseDTO;
import it.epicode.santuario.domicilio.Domicilio;
import lombok.Data;

import java.util.List;

@Data
public class UtenteResponseDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String recapitoTelefonico;
    private String fotoUser;
    private Domicilio domicilio;
    @JsonIgnoreProperties("proprietario")
    private List<AnimaleResponseDTO> animaliGestiti;
}