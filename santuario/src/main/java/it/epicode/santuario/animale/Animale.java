package it.epicode.santuario.animale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.santuario.utente.Utente;
import it.epicode.santuario.vaccinazione.Vaccinazione;
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
@Table(name = "animali")
public class Animale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;
    @Column (length = 1000,nullable = true)
    private String fotoUrl;

    @Column(nullable = false)
    private LocalDate dataNascita;

    @Column(length = 200, nullable = false)
    private String statoSalute;

    @Column(length = 50, nullable = false)
    private String razza;

    @Column(length = 50, nullable = false)
    private String mantello;

    @Column(nullable = false)
    private boolean sterilizzato;


    @ManyToMany(mappedBy = "animali", cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<Vaccinazione> vaccinazioni;

    @Column(nullable = false)
    private boolean microchip;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private TipoAnimale tipo;

    @Enumerated(EnumType.STRING)
    private AdoptionStatus adoptionStatus;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente proprietario;

    public enum TipoAnimale {
        CANE,
        GATTO
    }
    public enum AdoptionStatus {
        DISPONIBILE,
        ADOTTATO

    }
}
