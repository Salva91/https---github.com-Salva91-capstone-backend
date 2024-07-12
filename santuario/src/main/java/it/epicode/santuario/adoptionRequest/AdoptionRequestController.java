package it.epicode.santuario.adoptionRequest;

import it.epicode.santuario.utente.Utente;
import it.epicode.santuario.utente.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adoption-requests")
public class AdoptionRequestController {
    @Autowired
    private AdoptionRequestService adoptionRequestService;
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<AdoptionRequest> getAllAdoptionRequests() {
        return adoptionRequestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionRequest> getAdoptionRequestById(@PathVariable Long id) {
        Optional<AdoptionRequest> adoptionRequest = adoptionRequestService.findById(id);
        return adoptionRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AdoptionRequest createAdoptionRequest(@RequestBody AdoptionRequest adoptionRequest) {
        return adoptionRequestService.save(adoptionRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionRequest> updateAdoptionRequest(@PathVariable Long id, @RequestBody AdoptionRequest adoptionRequestDetails) {
        Optional<AdoptionRequest> adoptionRequest = adoptionRequestService.findById(id);
        if (adoptionRequest.isPresent()) {
            adoptionRequestDetails.setId(id);
            return ResponseEntity.ok(adoptionRequestService.save(adoptionRequestDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdoptionRequest(@PathVariable Long id) {
        if (adoptionRequestService.findById(id).isPresent()) {
            adoptionRequestService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/adottare/{animaleId}/{utenteId}")
    public String adottareAnimale(@PathVariable Long animaleId, @PathVariable Long utenteId) {
        return adoptionRequestService.adottareAnimale(animaleId, utenteId);
    }

    @PostMapping("/adottare/{animaleId}")
    public String adottareAnimale(@PathVariable Long animaleId, @RequestBody Utente utente) {
        return adoptionRequestService.adottareAnimale(animaleId, utente);
    }

}
