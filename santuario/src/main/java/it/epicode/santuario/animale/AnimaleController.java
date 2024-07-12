package it.epicode.santuario.animale;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/animali")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AnimaleController {
    private final AnimaleService animaleService;
    private final AnimaleRepository animaleRepository;
    @PostMapping
    public ResponseEntity<AnimaleResponseDTO> createAnimale (@RequestBody AnimaleRequestDTO animaleRequestDTO){

        AnimaleResponseDTO AnimaleResponseDTO = animaleService.createAnimale(animaleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(AnimaleResponseDTO);

    }
    @PutMapping("/{id}")
    public ResponseEntity<AnimaleResponseDTO> modifyAnimale (@PathVariable Long id, @RequestBody AnimaleRequestDTO animaleRequestDTO){

        AnimaleResponseDTO animaleResponseDTO = animaleService.modifyAnimale(id,animaleRequestDTO);
        return ResponseEntity.ok(animaleResponseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimale(@PathVariable Long id){
        String message = animaleService.deleteAnimale(id);
        return ResponseEntity.ok(message);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Animale> getAnimaleById(@PathVariable Long id) {
        Optional<Animale> animale = animaleService.getAnimaleById(id);
        return animale.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimaleResponseSenzaInfoProprietarioDTO> getAnimaleDetailById(@PathVariable Long id) {
        AnimaleResponseSenzaInfoProprietarioDTO response = animaleService.getAnimaleDetailById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AnimaleResponseDTO> patchAnimale(
            @PathVariable Long id,
            @RequestBody AnimaleRequestDTO animaleRequestDTO) {

        AnimaleResponseDTO responseDTO = animaleService.patchAnimale(id, animaleRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Animale> getAnimaliByTipo(@PathVariable Animale.TipoAnimale tipo) {
        return animaleService.findByTipo(tipo);
    }
    @GetMapping
    public List<AnimaleResponseDTO> findAll() {
        return animaleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private AnimaleResponseDTO convertToDTO(Animale animale) {
        AnimaleResponseDTO dto = new AnimaleResponseDTO();
        dto.setId(animale.getId());
        dto.setNome(animale.getNome());
        dto.setFotoUrl(animale.getFotoUrl());
        dto.setAnni(animale.getAnni());
        dto.setDescrizione(animale.getDescrizione());
        dto.setStatoSalute(animale.getStatoSalute());
        dto.setRazza(animale.getRazza());
        dto.setMantello(animale.getMantello());
        dto.setSterilizzato(animale.isSterilizzato());
        dto.setMicrochip(animale.isMicrochip());
        dto.setTipo(animale.getTipo());
        dto.setProprietario(animale.getProprietario());
        dto.setAdoptionStatus(animale.getAdoptionStatus());
        return dto;
    }

}