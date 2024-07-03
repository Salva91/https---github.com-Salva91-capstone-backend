package it.epicode.santuario.animale;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.epicode.santuario.utente.UtenteSummaryDTO;
import it.epicode.santuario.vaccinazione.VaccinazioneResponseDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimaleResponseSenzaInfoProprietarioDTO {

        private Long id;
        private String nome;
        private String fotoUrl;
        private String dataNascita;
        private String statoSalute;
        private String razza;
        private String mantello;
        private Boolean sterilizzato;
        private Boolean microchip;
        private String tipo;
        private List<VaccinazioneResponseDTO> vaccinazioni;
        private UtenteSummaryDTO proprietario; // Usare il nuovo DTO
    }


