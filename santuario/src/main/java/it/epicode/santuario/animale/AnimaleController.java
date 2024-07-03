package it.epicode.santuario.animale;

import it.epicode.santuario.vaccinazione.VaccinazioneRequestDTO;
import it.epicode.santuario.vaccinazione.VaccinazioneResponseDTO;
import it.epicode.santuario.vaccinazione.VaccinazioneResponsePrj;
import it.epicode.santuario.vaccinazione.VaccinazioneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/animali")
@RequiredArgsConstructor
public class AnimaleController {
    private final AnimaleService animaleService;
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


   // @GetMapping("/{id}")
  //  public ResponseEntity<AnimaleResponseDTO> findById(@PathVariable Long id) {
      //  AnimaleResponseDTO response = animaleService.getAnimaleDetailById(id);
      //  return ResponseEntity.ok(response);
    //}

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

}