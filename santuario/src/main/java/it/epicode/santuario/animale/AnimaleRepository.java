package it.epicode.santuario.animale;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface AnimaleRepository extends JpaRepository<Animale,Long> {
    public List<Animale> findByNome (String nome);
    List<Animale> findByTipo(Animale.TipoAnimale tipo);


}
