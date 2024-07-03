package it.epicode.santuario.domicilio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.santuario.utente.Utente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String via;
    private String citta;
    private String cap;
    @JsonIgnore
    @OneToOne(mappedBy = "domicilio")
    private Utente utente;

}
