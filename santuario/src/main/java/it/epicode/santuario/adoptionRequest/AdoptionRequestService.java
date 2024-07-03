package it.epicode.santuario.adoptionRequest;

import it.epicode.santuario.adoptionRequest.AdoptionRequest;
import it.epicode.santuario.adoptionRequest.AdoptionRequestRepository;
import it.epicode.santuario.animale.*;
import it.epicode.santuario.utente.Utente;
import it.epicode.santuario.utente.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

//import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdoptionRequestService {
    @Autowired
    private AdoptionRequestRepository adoptionRequestRepository;
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    AnimaleRepository animaleRepository;

    public List<AdoptionRequest> findAll() {
        return adoptionRequestRepository.findAll();
    }

    public Optional<AdoptionRequest> findById(Long id) {
        return adoptionRequestRepository.findById(id);
    }

    public AdoptionRequest save(AdoptionRequest adoptionRequest) {
        return adoptionRequestRepository.save(adoptionRequest);
    }

    public void deleteById(Long id) {
        adoptionRequestRepository.deleteById(id);
    }




    @Transactional
    public String adottareAnimale(Long animaleId, Long utenteId) {
        Optional<Animale> optionalAnimale = animaleRepository.findById(animaleId);
        if (optionalAnimale.isEmpty()) {
            return "Animale non trovato.";
        }

        Animale animale = optionalAnimale.get();
        if (animale.getAdoptionStatus() != Animale.AdoptionStatus.DISPONIBILE) {
            return "L'animale non è disponibile per l'adozione.";
        }

        // Recupera l'utente che sta adottando l'animale
        Optional<Utente> optionalUtente = utenteRepository.findById(utenteId);
        if (optionalUtente.isEmpty()) {
            return "Utente non trovato.";
        }
        Utente utente = optionalUtente.get();

        // Associa l'animale all'utente
        animale.setProprietario(utente);

        // Imposta lo stato di adozione dell'animale
        animale.setAdoptionStatus(Animale.AdoptionStatus.ADOTTATO);
        animaleRepository.save(animale);

        return "L'animale è stato adottato con successo.";
    }
        }





