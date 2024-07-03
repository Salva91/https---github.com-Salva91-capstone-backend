package it.epicode.santuario.utente;

import it.epicode.santuario.domicilio.Domicilio;
import lombok.Data;

@Data
public class UtenteRequestDTO {
    private String nome;
    private String cognome;
    private String recapitoTelefonico;
    private String fotoUser;
    private Domicilio domicilio;

}
