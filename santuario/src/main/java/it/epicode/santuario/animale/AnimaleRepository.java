package it.epicode.santuario.animale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnimaleRepository extends JpaRepository<Animale,Long> {
    public List<Animale> findByNome (String nome);
    @Query("SELECT a FROM Animale a JOIN FETCH a.vaccinazioni WHERE a.id = :id")
    Optional<Animale> findByIdWithVaccinazioni(@Param("id") Long id);


}
