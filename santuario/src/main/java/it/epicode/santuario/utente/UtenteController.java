package it.epicode.santuario.utente;


import it.epicode.santuario.security.ApiValidationException;
import it.epicode.santuario.security.LoginModel;
import it.epicode.santuario.security.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
@RequiredArgsConstructor
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping
    public List<PersonaResponsePrj> getAllUtenti() {
        return utenteService.findAll();
    }

    @PostMapping
    public ResponseEntity<UtenteResponseDTO> createUtente (@RequestBody UtenteRequestDTO utenteRequestDTO){

        UtenteResponseDTO utenteResponseDTO = utenteService.createUtente(utenteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(utenteResponseDTO);

    }
    @PutMapping("/{id}")
    public ResponseEntity<UtenteResponseDTO> modifyUtente (@PathVariable Long id, @RequestBody UtenteRequestDTO utenteRequestDTO){

        UtenteResponseDTO utenteResponseDTO = utenteService.modifyUtente(id,utenteRequestDTO);
        return ResponseEntity.ok(utenteResponseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUtente(@PathVariable Long id){
        String message = utenteService.deleteUtente(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteResponseDTO> findUtenteById(@PathVariable Long id) {
        UtenteResponseDTO response = utenteService.findUtenteById(id);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/registerAdmin")
    public ResponseEntity<UtenteRequestSecurityDTO> registerAdmin(@RequestBody UtenteRequestSecurityDTO registerUser){
        return ResponseEntity.ok(utenteService.registerAdmin(registerUser));
    }

    @PostMapping("/register")
    public ResponseEntity<UtenteRequestSecurityDTO> register(@RequestBody @Validated UtenteRequestSecurityDTO register, BindingResult validator){
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }
        var registeredUser = utenteService.register(
                UtenteRequestSecurityDTO.builder()
                        .withNome(register.getNome())
                        .withCognome(register.getCognome())
                        .withUsername(register.getUsername())
                        .withEmail(register.getEmail())
                        .withPassword(register.getPassword())
                        .withDomicilio(register.getDomicilio())
                        .withRecapitoTelefonico(register.getRecapitoTelefonico())
                        .build());

        return  new ResponseEntity<> (registeredUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw  new ApiValidationException(validator.getAllErrors());
        }
        return new ResponseEntity<>(utenteService.login(model.username(), model.password()).orElseThrow(), HttpStatus.OK);
    }





}
