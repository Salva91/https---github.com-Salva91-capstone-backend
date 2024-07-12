package it.epicode.santuario.animale;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AnimaleResponseRidottoDTO {
    private Long id;
    private String nome;
    private String fotoUrl;
    private LocalDate anni;
    private String Descrizione;
    private String statoSalute;
    private String razza;
    private String mantello;
    private boolean sterilizzato;
    private boolean microchip;
    private Animale.TipoAnimale tipo;
}
