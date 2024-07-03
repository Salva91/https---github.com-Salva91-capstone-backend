package it.epicode.santuario.utente;

import it.epicode.santuario.animale.Animale;
import it.epicode.santuario.animale.AnimaleRepository;
import it.epicode.santuario.animale.AnimaleResponseDTO;
//import it.epicode.santuario.email.EmailService;
import it.epicode.santuario.domicilio.Domicilio;
import it.epicode.santuario.security.*;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtenteService {

    @Autowired
    UtenteRepository utenteRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder encoder;
   // private final EmailService emailService;
    private final AuthenticationManager auth;
    private final JwtUtils jwt;
    private final LoginResponseDTO loginResponseDTO;
    private final RegisteredUserDTO registeredUserDTO;
    private final RegisterUserDTO registerUserDTO;










    //POST per la creazione dell'utente (verificando se già esiste altrimeti me lo crea nella repo)
    @Transactional
    public UtenteResponseDTO createUtente(UtenteRequestDTO utenteRequestDTO) {
        if (utenteRepository.existsByRecapitoTelefonicoAndNomeAndCognome(utenteRequestDTO.getRecapitoTelefonico(), utenteRequestDTO.getNome(), utenteRequestDTO.getCognome())) {
            throw new EntityExistsException("L'utente esiste gia' ");
        }
        Utente entity = new Utente();
        BeanUtils.copyProperties(utenteRequestDTO, entity);
        UtenteResponseDTO utenteResponseDTO = new UtenteResponseDTO();
        BeanUtils.copyProperties(entity, utenteResponseDTO);
        utenteRepository.save(entity);
        return utenteResponseDTO;


    }

    //put per la modifica dei dati dell'utente

    public UtenteResponseDTO modifyUtente(Long id, UtenteRequestDTO utenteRequestDTO) {
        // Questo metodo modifica un entity esistente.
        // Prima verifica se l'entity esiste nel database. Se non esiste, viene generata un'eccezione.
        if (!utenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        // Se l'entity esiste, le sue proprietà vengono modificate con quelle presenti nell'oggetto PersonaRequest.
        Utente entity = utenteRepository.findById(id).get();
        BeanUtils.copyProperties(utenteRequestDTO, entity);
        // L'entity modificato viene quindi salvato nel database e le sue proprietà vengono copiate in un oggetto PersonaResponse.
        utenteRepository.save(entity);
        UtenteResponseDTO response = new UtenteResponseDTO();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    //DELETE per la cancellazione dei dati dell'utente
    public String deleteUtente(Long id) {
        // Questo metodo elimina un Persona dal database.
        // Prima verifica se l'Persona esiste nel database. Se non esiste, viene generata un'eccezione.
        if (!utenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        // Se l'Persona esiste, viene eliminato dal database.
        utenteRepository.deleteById(id);
        return "Utente eliminato";
    }

    // GET ALL
    public List<PersonaResponsePrj> findAll(){
        // Questo metodo restituisce tutti gli autori presenti nel database utilizzando una repository Projection che restituisce un Dto
        return utenteRepository.findAllBy();
    }

    public UtenteResponseDTO findUtenteById(Long id) {
        Utente entity = utenteRepository.findByIdWithAnimali(id)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        UtenteResponseDTO response = new UtenteResponseDTO();
        BeanUtils.copyProperties(entity, response);

        // Converti gli animali gestiti in DTO
        List<AnimaleResponseDTO> animaliGestitiDTO = entity.getAnimaliGestiti().stream()
                .map(this::convertToAnimaleResponseDTO)
                .collect(Collectors.toList());
        response.setAnimaliGestiti(animaliGestitiDTO);

        return response;
    }


    private AnimaleResponseDTO convertToAnimaleResponseDTO(Animale animale) {
        AnimaleResponseDTO dto = new AnimaleResponseDTO();
        BeanUtils.copyProperties(animale, dto);
        return dto;
    }

    public UtenteRequestSecurityDTO registerAdmin(UtenteRequestSecurityDTO register){
        if(utenteRepository.existsByUsername(register.getUsername())){
            throw new EntityExistsException("Utente gia' esistente");
        }

        Roles roles = rolesRepository.findById(Roles.ROLES_ADMIN).get();
        Utente u = new Utente();
        BeanUtils.copyProperties(register, u);
        u.setPassword(encoder.encode(register.getPassword()));
        Domicilio domicilio = new Domicilio();
        BeanUtils.copyProperties(register.getDomicilio(), domicilio);
        u.setDomicilio(domicilio);
        u.getRoles().add(roles);
        utenteRepository.save(u);
        UtenteRequestSecurityDTO response = new UtenteRequestSecurityDTO();
        BeanUtils.copyProperties(u, response);
        response.setRoles(List.of(roles));
        return response;

    }

    public UtenteRequestSecurityDTO register(UtenteRequestSecurityDTO register){
        if(utenteRepository.existsByUsername(register.getUsername())){
            throw new EntityExistsException("Utente gia' esistente");
        }

        Roles roles = rolesRepository.findById(Roles.ROLES_USER).get();
        /*
        if(!rolesRepository.existsById(Roles.ROLES_USER)){
            roles = new Roles();
            roles.setRoleType(Roles.ROLES_USER);
        } else {
            roles = rolesRepository.findById(Roles.ROLES_USER).get();
        }

         */
        Utente u = new Utente();
        BeanUtils.copyProperties(register, u);
        u.setPassword(encoder.encode(register.getPassword()));
        Domicilio domicilio = new Domicilio();
        BeanUtils.copyProperties(register.getDomicilio(), domicilio);
        u.setDomicilio(domicilio);

// Inizializza la lista dei ruoli se è null
        if (u.getRoles() == null) {
            u.setRoles(new ArrayList<>());
        }
        u.getRoles().add(roles); // Aggiungi il ruolo alla lista dei ruoli

        utenteRepository.save(u);

        UtenteRequestSecurityDTO response = new UtenteRequestSecurityDTO();
        BeanUtils.copyProperties(u, response);
        response.setRoles(u.getRoles());
        //emailService.sendWelcomeEmail(u.getEmail());

        return response;

    }

    @Autowired
    private JwtUtils jwtUtils;

    public Optional<LoginResponseDTO> login(String username, String password) {
        try {
            Authentication authentication = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            SecurityUserDetails userPrincipal = (SecurityUserDetails) authentication.getPrincipal();

            LoginResponseDTO dto = LoginResponseDTO.builder()
                    .withUser(buildRegisteredUserDTO(userPrincipal))
                    .build();

            // Genera il token JWT includendo le informazioni dell'utente
            dto.setToken(jwtUtils.generateToken(authentication));

            return Optional.of(dto);
        } catch (NoSuchElementException e) {
           // log.error("User not found", e); mi genera errore, lo aggiusterò poi
            throw new InvalidLoginException(username, password);
        } catch (AuthenticationException e) {
           // log.error("Authentication failed", e); anche qui mi genera errore, lo modificherò poi
            throw new InvalidLoginException(username, password);
        }


    }

    private RegisteredUserDTO buildRegisteredUserDTO(SecurityUserDetails userDetails) {
        RegisteredUserDTO userDto = new RegisteredUserDTO();
        userDto.setId(userDetails.getUserId());
        userDto.setEmail(userDetails.getEmail());
        userDto.setRoles(userDetails.getRoles());
        userDto.setUsername(userDetails.getUsername());
        userDto.setName(userDetails.getName());
        userDto.setSurname(userDetails.getSurname());
        // Aggiungi altri campi se necessario

        return userDto;


    }

    // Metodo per ottenere l'ID dell'utente dal contesto di sicurezza
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUserDetails) {
            SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
            return userDetails.getUserId();
        }
        throw new IllegalStateException("Utente non autenticato");
    }







    }