package morango_esmeralda.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import morango_esmeralda.domain.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nome", length = 40, nullable = false)
    private String nome;

    @Column(name = "senha", length = 245, nullable = false)
    private String senha;

    @Column(name = "email", length = 40, nullable = false)
    private String email;


    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Usuario(String nome, String senha, UserRole role,String endereco) {
        this.nome = nome;
        this.senha = senha;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
