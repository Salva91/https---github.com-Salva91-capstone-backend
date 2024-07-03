package it.epicode.santuario.vaccinazione;

import it.epicode.santuario.animale.Animale;
import it.epicode.santuario.animale.AnimaleRepository;
import it.epicode.santuario.animale.AnimaleRequestDTO;
import it.epicode.santuario.animale.AnimaleResponseDTO;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinazioneService {

    @Autowired
    VaccinazioneRepository vaccinazioneRepository;
    @Autowired
    AnimaleRepository animaleRepository;

    @Transactional
    public VaccinazioneResponseDTO createVaccinazione(VaccinazioneRequestDTO vaccinazioneRequestDTO) {
        Long animaleId = vaccinazioneRequestDTO.getAnimaleId();

        // Controllo aggiuntivo per verificare se animaleId è null
        if (animaleId == null) {
            throw new IllegalArgumentException("ID dell'animale non può essere null");
        }

        // Controlla se l'animale con l'ID specificato esiste nel database
        Animale animale = animaleRepository.findById(animaleId)
                .orElseThrow(() -> new EntityNotFoundException("Animale non trovato con id: " + animaleId));

        // Continua con la creazione della vaccinazione
        Vaccinazione vaccinazione = new Vaccinazione();
        BeanUtils.copyProperties(vaccinazioneRequestDTO, vaccinazione);
        vaccinazione.setScadenzaVaccinazione(calculateScadenza(vaccinazione.getDataVaccinazione(), vaccinazioneRequestDTO.getDurataInMesi()));
        vaccinazione.setAnimali(List.of(animale));

        vaccinazioneRepository.save(vaccinazione);

        VaccinazioneResponseDTO responseDTO = new VaccinazioneResponseDTO();
        BeanUtils.copyProperties(vaccinazione, responseDTO);
        responseDTO.setAnimaleVaccinato(animale);
        return responseDTO;
    }


//put per la modifica dei dati dell'vaccino

    @Transactional
    public VaccinazioneResponseDTO modifyVaccino(Long id, VaccinazioneRequestDTO vaccinazioneRequestDTO) {
        Vaccinazione vaccinazione = vaccinazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vaccino non trovato"));

        Animale animale = animaleRepository.findById(vaccinazioneRequestDTO.getAnimaleId())
                .orElseThrow(() -> new EntityNotFoundException("Animale non trovato"));

        BeanUtils.copyProperties(vaccinazioneRequestDTO, vaccinazione);
        vaccinazione.setScadenzaVaccinazione(calculateScadenza(vaccinazione.getDataVaccinazione(), vaccinazioneRequestDTO.getDurataInMesi()));
        vaccinazione.setAnimali(List.of(animale));

        vaccinazioneRepository.save(vaccinazione);

        VaccinazioneResponseDTO response = new VaccinazioneResponseDTO();
        BeanUtils.copyProperties(vaccinazione, response);
        response.setAnimaleVaccinato(animale);
        return response;
    }


    //DELETE per la cancellazione dei dati del vaccino
    public String deleteVaccinazione(Long id) {
        // Questo metodo elimina un Persona dal database.
        // Prima verifica se l'Persona esiste nel database. Se non esiste, viene generata un'eccezione.
        if (!vaccinazioneRepository.existsById(id)) {
            throw new EntityNotFoundException("vaccino non trovato");
        }
        // Se l'Persona esiste, viene eliminato dal database.
        vaccinazioneRepository.deleteById(id);
        return "vaccino eliminato";
    }

    public List<VaccinazioneResponsePrj> findAll() {
        // Questo metodo restituisce tutti gli autori presenti nel database utilizzando una repository Projection che restituisce un Dto
        return vaccinazioneRepository.findAllBy();
    }

    public VaccinazioneResponseDTO findVaccinazioneById(Long id) {
        // Questo metodo cerca un entity nel database utilizzando l'ID.
        // Se l'entity non esiste, viene generata un'eccezione.
        if (!vaccinazioneRepository.existsById(id)) {
            throw new EntityNotFoundException("Vaccino non trovato");
        }
        // Se l'entity esiste, viene recuperato e le sue proprietà vengono copiate in un oggetto PersonaResponse.
        Vaccinazione entity = vaccinazioneRepository.findById(id).get();
        VaccinazioneResponseDTO response = new VaccinazioneResponseDTO();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private LocalDate calculateScadenza(LocalDate dataVaccinazione, int durataInMesi) {
        return dataVaccinazione.plusMonths(durataInMesi);
    }
}