package com.rangotech.springsecurityapp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Size(min = 5, max = 20, message = "El nombre debe estar contenido entre 5 y 20 caracteres de longitud")
    private String name;
    @Size(min = 10, max = 10, message = "El numero de telefono debe contener exactamente 10 caracteres")

    @Pattern(regexp = "\\d{10}$")
    private String phone;
    private LocalDateTime createdDate;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    /*Los roles no se pueden repetir*/
    private Set<Role> roles = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Email
    @Column(unique = true, nullable = false)
    @NotBlank
    private String username;
    private String password;
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonIgnore
    private Cart cart;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getRole())
        ).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
