package it.epicode.santuario.security;
import it.epicode.santuario.utente.Utente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class SecurityUserDetails implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    @Builder.Default
    private boolean accountNonExpired = true;
    @Builder.Default
    private boolean accountNonLocked = true;
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @Builder.Default
    private boolean enabled = true;

    private Long userId;
    private String email;
    private List<Roles> roles;

    private String name;
    private String surname;

    public static SecurityUserDetails build(Utente utente) {
        var authorities = utente.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleType())).toList();
        return SecurityUserDetails.builder()
                .withUsername(utente.getUsername())
                .withPassword(utente.getPassword())
                .withAuthorities(authorities)
                .withUserId(utente.getId())
                .withEmail(utente.getEmail())
                .withRoles(utente.getRoles())
                .withName(utente.getNome())
                .withSurname(utente.getCognome())
                .build();
    }
}
