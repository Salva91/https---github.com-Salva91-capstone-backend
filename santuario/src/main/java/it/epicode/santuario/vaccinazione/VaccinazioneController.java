package it.epicode.santuario.vaccinazione;

import it.epicode.santuario.utente.PersonaResponsePrj;
import it.epicode.santuario.utente.UtenteRequestDTO;
import it.epicode.santuario.utente.UtenteResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccinazioni")
@RequiredArgsConstructor
public class VaccinazioneController {

    @Autowired
    VaccinazioneService vaccinazioneService;

    @GetMapping
    public List<VaccinazioneResponsePrj> getAllUtenti() {
        return vaccinazioneService.findAll();
    }

    @PostMapping
    public ResponseEntity<VaccinazioneResponseDTO> createVaccinazioni (@RequestBody VaccinazioneRequestDTO vaccinazioneRequestDTO){

        VaccinazioneResponseDTO vaccinazioneResponseDTO = vaccinazioneService.createVaccinazione(vaccinazioneRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vaccinazioneResponseDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccinazioneResponseDTO> modifyVaccino (@PathVariable Long id, @RequestBody VaccinazioneRequestDTO vaccinazioneRequestDTO){

        VaccinazioneResponseDTO vaccinazioneResponseDTO = vaccinazioneService.modifyVaccino(id,vaccinazioneRequestDTO);
        return ResponseEntity.ok(vaccinazioneResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaccinazione(@PathVariable Long id){
        String message = vaccinazioneService.deleteVaccinazione(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccinazioneResponseDTO> findVaccinazioneById(@PathVariable Long id) {
        VaccinazioneResponseDTO response = vaccinazioneService.findVaccinazioneById(id);
        return ResponseEntity.ok(response);
    }

}
