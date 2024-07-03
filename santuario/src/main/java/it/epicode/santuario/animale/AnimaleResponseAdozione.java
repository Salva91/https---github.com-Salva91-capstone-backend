package it.epicode.santuario.animale;

import it.epicode.santuario.vaccinazione.VaccinazioneResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class AnimaleResponseAdozione {
    private Long id;
    private String nome;
    private String fotoUrl;
    private LocalDate dataNascita;
    private String statoSalute;
    private String razza;
    private String mantello;
    private boolean sterilizzato;
    private List<String> vaccinazioni;
    private boolean microchip;
    private Animale.TipoAnimale tipo;
    private Animale.AdoptionStatus statoAdozione;
    private String messaggio;
}
