package it.epicode.santuario.animale;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.epicode.santuario.utente.UtenteSummaryDTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimaleResponseSenzaInfoProprietarioDTO {

        private Long id;
        private String nome;
        private String fotoUrl;
        private LocalDate anni;
        private String Descrizione;
        private String statoSalute;
        private String razza;
        private String mantello;
        private Boolean sterilizzato;
        private Boolean microchip;
        private String tipo;
        private UtenteSummaryDTO proprietario; // Usare il nuovo DTO
    }


