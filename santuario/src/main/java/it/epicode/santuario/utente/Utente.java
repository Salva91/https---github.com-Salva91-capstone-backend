package it.epicode.santuario.utente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.epicode.santuario.animale.Animale;
import it.epicode.santuario.domicilio.Domicilio;
import it.epicode.santuario.security.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    private String username;

    private String email;
    @Column(length = 125, nullable = false)
    private String password;

    @Column(nullable = true)
    private String recapitoTelefonico;

    @Column(nullable = true)
    private String fotoUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    private Domicilio domicilio;


    @JsonIgnoreProperties("proprietario")
    @JsonManagedReference
    @OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animale> animaliGestiti;

    @ManyToMany(fetch = FetchType.EAGER)

    private List<Roles> roles = new ArrayList<>();

}
