package morango_esmeralda.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import morango_esmeralda.enums.TipoUsuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

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

    @Column(name = "data_nasc", length = 40, nullable = false)
    private Date dataNasc;

    @Column(name = "telefone", length = 40, nullable = false)
    private String telefone;


    @Enumerated(EnumType.STRING)
    @Column()
    private TipoUsuario tipo;

    public Usuario(String nome, String senha, String email, Date data_nasc, String telefone, TipoUsuario tipo) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNasc = data_nasc;
        this.telefone = telefone;
        this.tipo = tipo;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(tipo.getRole());
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return senha;
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
