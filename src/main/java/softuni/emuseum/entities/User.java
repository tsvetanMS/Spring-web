package softuni.emuseum.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    private String username;
    private String password;
    private String email;

    private Set<Role> authorities;


    public User() {
    }

//----------------------------------------------------------------------------------------------------------------------
    @Override
    @Column(name = "username", nullable = false, unique = true, updatable = false)
    @Length(min = 3)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    @Column(name = "password", nullable = false)
    @Length(min = 3)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Column(name = "email", nullable = false, unique = true)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//----------------------------------------------------------------------------------------------------------------------
    // ако дам тук //cascade = CascadeType.ALL немога да трия потребител
    // защото иска да изтрие и ролите
    @Override
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
//----------------------------------------------------------------------------------------------------------------------
    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
//----------------------------------------------------------------------------------------------------------------------
}
