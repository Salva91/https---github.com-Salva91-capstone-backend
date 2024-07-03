package it.epicode.santuario.utente;

import it.epicode.santuario.animale.AnimaleResponseRidottoDTO;
import it.epicode.santuario.domicilio.Domicilio;
import lombok.Data;

import java.util.List;

@Data
public class UtenteResponseRidottoDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String recapitoTelefonico;
    private String fotoUser;
    private Domicilio domicilio;
    private List<AnimaleResponseRidottoDTO> animaliGestiti;
}