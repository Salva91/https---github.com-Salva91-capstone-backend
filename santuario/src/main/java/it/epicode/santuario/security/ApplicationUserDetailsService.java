package it.epicode.santuario.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import it.epicode.santuario.utente.UtenteRepository;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    UtenteRepository user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var utente = user.findOneByUsername(username).orElseThrow();
        return SecurityUserDetails.build(utente);
    }

}