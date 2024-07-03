package it.epicode.santuario.utente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Long> {

   // public Utente findByRecapitoTelefonicoAndNomeandCognome(String numeroTelefono, String nome, String cognome);
   public boolean existsByRecapitoTelefonicoAndNomeAndCognome(String recapitoTelefonico, String nome, String cognome);


    @Query("SELECT u FROM Utente u LEFT JOIN FETCH u.animaliGestiti WHERE u.id = :id")
    Optional<Utente> findByIdWithAnimali(@Param("id") Long id);

    List<PersonaResponsePrj> findAllBy();

    Optional<Utente> findOneByUsername(String username);

    public boolean existsByUsername(String username);
}

