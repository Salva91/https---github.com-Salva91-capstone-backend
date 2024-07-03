package it.epicode.santuario.animale;

import it.epicode.santuario.utente.Utente;
import it.epicode.santuario.vaccinazione.Vaccinazione;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AnimaleRequestDTO {
    private String nome;
    private String fotoUrl;
    private LocalDate dataNascita;
    private String statoSalute;
    private String razza;
    private String mantello;
    private boolean sterilizzato;
    private List<Vaccinazione> vaccinazioni;
    private boolean microchip;
    private Animale.TipoAnimale tipo;
    private Long proprietarioId;
    private Animale.AdoptionStatus adoptionStatus;
}