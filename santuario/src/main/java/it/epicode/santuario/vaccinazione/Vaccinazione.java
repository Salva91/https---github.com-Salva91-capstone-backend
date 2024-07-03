package it.epicode.santuario.vaccinazione;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.santuario.animale.Animale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vaccinazioni")
public class Vaccinazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nomeVaccino;

    @Column(nullable = false)
    private LocalDate dataVaccinazione;

    @Column(nullable = false)
    private LocalDate scadenzaVaccinazione;

    @Column(nullable = false)
    private int durataInMesi;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "vaccinazione_animale",
            joinColumns = @JoinColumn(name = "vaccinazione_id"),
            inverseJoinColumns = @JoinColumn(name = "animale_id")
    )

    private List<Animale> animali;



}
