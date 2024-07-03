package it.epicode.santuario.vaccinazione;


import it.epicode.santuario.animale.Animale;
import it.epicode.santuario.utente.PersonaResponsePrj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface VaccinazioneRepository extends JpaRepository<Vaccinazione, Long> {
    public List<VaccinazioneResponsePrj> findAllBy();





}
