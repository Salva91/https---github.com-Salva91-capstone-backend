package it.epicode.santuario.vaccinazione;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.epicode.santuario.animale.Animale;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VaccinazioneResponseDTO {
    private Long id;
    private String nomeVaccino;
    private LocalDate dataVaccinazione;
    private LocalDate scadenzaVaccinazione;
    private Animale animaleVaccinato;


}
