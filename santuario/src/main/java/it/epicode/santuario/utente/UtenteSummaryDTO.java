package it.epicode.santuario.utente;


import com.fasterxml.jackson.annotation.JsonInclude;
import it.epicode.santuario.domicilio.Domicilio;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtenteSummaryDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String recapitoTelefonico;
    private String fotoUser;
    private Domicilio domicilio;
}
