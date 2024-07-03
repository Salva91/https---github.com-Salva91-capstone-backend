package it.epicode.santuario.adoptionRequest;

import it.epicode.santuario.animale.Animale;
import it.epicode.santuario.utente.Utente;

import java.util.List;

public class AdoptionRequestResponseDTO {
    private Long id;
    private Utente utente;
    private List<Animale> animali;
}
