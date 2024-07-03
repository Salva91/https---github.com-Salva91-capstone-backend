package it.epicode.santuario.animale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.santuario.utente.Utente;
import it.epicode.santuario.vaccinazione.Vaccinazione;
import it.epicode.santuario.vaccinazione.VaccinazioneResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AnimaleResponseDTO {
    private Long id;
    private String nome;
    private String fotoUrl;
    private LocalDate dataNascita;
    private String statoSalute;
    private String razza;
    private String mantello;
    private boolean sterilizzato;
    private boolean microchip;
    private Animale.TipoAnimale tipo;
    @JsonIgnore
    private Utente proprietario;
    private Animale.AdoptionStatus adoptionStatus;

    public enum TipoAnimale {
        CANE,
        GATTO
    }
    public enum AdoptionStatus {
        DISPONIBILE,
        ADOTTATO

    }
}