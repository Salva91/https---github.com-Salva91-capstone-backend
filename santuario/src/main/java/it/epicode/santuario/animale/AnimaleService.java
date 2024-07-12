package it.epicode.santuario.animale;


import it.epicode.santuario.utente.Utente;
import it.epicode.santuario.utente.UtenteRepository;
import it.epicode.santuario.utente.UtenteSummaryDTO;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimaleService {

    private final AnimaleRepository animaleRepository;
    private final UtenteRepository utenteRepository;



    // POST
    @Transactional
    public AnimaleResponseDTO createAnimale(AnimaleRequestDTO animaleRequest) {
        Animale animale = convertToEntity(animaleRequest);
        if (animaleRequest.getProprietarioId() != null) {
            if (!utenteRepository.existsById(animaleRequest.getProprietarioId())) {
                throw new EntityNotFoundException("Proprietario non trovato");
            }
            Utente proprietario = utenteRepository.findById(animaleRequest.getProprietarioId()).get();
            animale.setProprietario(proprietario);
        }
        animaleRepository.save(animale);
        return convertToDTO(animale);
    }


    // PUT
    public AnimaleResponseDTO modifyAnimale(Long id, AnimaleRequestDTO animaleRequest) {
        if (!animaleRepository.existsById(id)) {
            throw new EntityNotFoundException("Animale non trovato");
        }

        Animale animale = animaleRepository.findById(id).get();
        BeanUtils.copyProperties(animaleRequest, animale);

        // Aggiorna il proprietario se necessario
        if (!animale.getProprietario().getId().equals(animaleRequest.getProprietarioId())) {
            if (!utenteRepository.existsById(animaleRequest.getProprietarioId())) {
                throw new EntityNotFoundException("Proprietario non trovato");
            }
            Utente nuovoProprietario = utenteRepository.findById(animaleRequest.getProprietarioId()).get();
            animale.setProprietario(nuovoProprietario);
        }

        animaleRepository.save(animale);
        return convertToDTO(animale);
    }

    public List<Animale> findByTipo(Animale.TipoAnimale tipo) {
        return animaleRepository.findByTipo(tipo);
    }


    // GET
    public List<AnimaleResponseDTO> findAll() {
        return animaleRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public AnimaleResponseSenzaInfoProprietarioDTO getAnimaleDetailById(Long id) {
        Animale animale = animaleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animale non trovato"));

        AnimaleResponseSenzaInfoProprietarioDTO response = new AnimaleResponseSenzaInfoProprietarioDTO();
        BeanUtils.copyProperties(animale, response);

        // Popolare UtenteSummaryDTO
        Utente proprietario = animale.getProprietario();
        if (proprietario != null) {
            UtenteSummaryDTO utenteSummaryDTO = new UtenteSummaryDTO();
            BeanUtils.copyProperties(proprietario, utenteSummaryDTO);
            response.setProprietario(utenteSummaryDTO);
        } else {
            response.setProprietario(null);
        }
        // Popolare le vaccinazioni
       /* List<VaccinazioneResponseDTO> vaccinazioni = null;
        if (animale.getVaccinazioni() != null && !animale.getVaccinazioni().isEmpty()) {
            vaccinazioni = animale.getVaccinazioni().stream()
                    .map(vaccinazione -> {
                        VaccinazioneResponseDTO vaccinoDTO = new VaccinazioneResponseDTO();
                        BeanUtils.copyProperties(vaccinazione, vaccinoDTO);
                        return vaccinoDTO;
                    })
                    .collect(Collectors.toList());
        }
        response.setVaccinazioni(vaccinazioni);*/

        return response;
    }


    // DELETE
    @Transactional
    public String deleteAnimale(Long id) {
        if (!animaleRepository.existsById(id)) {
            throw new EntityNotFoundException("Animale non trovato");
        }
        animaleRepository.deleteById(id);
        return "Animale eliminato";
    }

    private Animale convertToEntity(AnimaleRequestDTO animaleRequestDTO) {
        Animale animale = new Animale();
        BeanUtils.copyProperties(animaleRequestDTO, animale);
        return animale;
    }

    private AnimaleResponseDTO convertToDTO(Animale animale) {
        AnimaleResponseDTO animaleResponseDTO = new AnimaleResponseDTO();
        BeanUtils.copyProperties(animale, animaleResponseDTO);

        // Converti le vaccinazioni in DTO
        //  List<VaccinazioneResponseDTO> vaccinazioniDTO = animale.getVaccinazioni().stream()
        //  .map(this::convertVaccinazioneToDTO)
        // .collect(Collectors.toList());

        // animaleResponseDTO.setVaccinazioni(vaccinazioniDTO);
        return animaleResponseDTO;
    }



    // ricerca per nome dell'animale
    public List<Animale> findByNome(Animale animale) {
        return animaleRepository.findByNome(animale.getNome());
    }

    public Optional<Animale> getAnimaleById(Long id) {
        return animaleRepository.findById(id);
    }


    @Transactional
    public AnimaleResponseDTO patchAnimale(Long id, AnimaleRequestDTO animaleRequestDTO) {
        Animale animale = animaleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animale non trovato"));

        // Aggiornamento del nome se presente nel payload della richiesta
        if (animaleRequestDTO.getNome() != null) {
            animale.setNome(animaleRequestDTO.getNome());
        }

        // Aggiornamento del mantello se presente nel payload della richiesta
        if (animaleRequestDTO.getMantello() != null) {
            animale.setMantello(animaleRequestDTO.getMantello());
        }




        if (animaleRequestDTO.getDescrizione() != null){
            animale.setDescrizione(animaleRequestDTO.getDescrizione());
        }

        // Aggiornamento dello stato di salute se presente nel payload della richiesta
        if (animaleRequestDTO.getStatoSalute() != null) {
            animale.setStatoSalute(animaleRequestDTO.getStatoSalute());
        }

        // Aggiornamento della razza se presente nel payload della richiesta
        if (animaleRequestDTO.getRazza() != null) {
            animale.setRazza(animaleRequestDTO.getRazza());
        }

        // Aggiornamento dello stato di sterilizzazione se presente nel payload della richiesta
        if (animaleRequestDTO.isSterilizzato() != animale.isSterilizzato()) {
            animale.setSterilizzato(animaleRequestDTO.isSterilizzato());
        }

// Aggiornamento dello stato del microchip se presente nel payload della richiesta
        if (animaleRequestDTO.isMicrochip() != animale.isMicrochip()) {
            animale.setMicrochip(animaleRequestDTO.isMicrochip());
        }


        // Aggiornamento del tipo se presente nel payload della richiesta
        if (animaleRequestDTO.getTipo() != null) {
            animale.setTipo(animaleRequestDTO.getTipo());
        }

        // Aggiornamento dello status di adozione se presente nel payload della richiesta
        if (animaleRequestDTO.getAdoptionStatus() != null) {
            animale.setAdoptionStatus(animaleRequestDTO.getAdoptionStatus());
        }

        // Aggiornamento del proprietario se è presente l'ID del nuovo proprietario nel payload
        if (animaleRequestDTO.getProprietarioId() != null &&
                !Objects.equals(animale.getProprietario().getId(), animaleRequestDTO.getProprietarioId())) {
            Utente nuovoProprietario = utenteRepository.findById(animaleRequestDTO.getProprietarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Proprietario non trovato"));
            animale.setProprietario(nuovoProprietario);
        }

        animaleRepository.save(animale);
        return convertToDTO(animale);
    }


}

