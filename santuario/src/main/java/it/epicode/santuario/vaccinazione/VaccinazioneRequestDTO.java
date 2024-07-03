package it.epicode.santuario.vaccinazione;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccinazioneRequestDTO {

    private String nomeVaccino;
    private LocalDate dataVaccinazione;
    private LocalDate scadenzaVaccinazione;
    private Long animaleId;
    private int durataInMesi;


}
