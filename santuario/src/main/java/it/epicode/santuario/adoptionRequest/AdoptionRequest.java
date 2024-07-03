package it.epicode.santuario.adoptionRequest;

import it.epicode.santuario.animale.Animale;
import it.epicode.santuario.utente.Utente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "adoption_requests")
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToMany
    @JoinTable(
            name = "adoption_request_animale",
            joinColumns = @JoinColumn(name = "adoption_request_id"),
            inverseJoinColumns = @JoinColumn(name = "animale_id")
    )
    private List<Animale> animali;
}
