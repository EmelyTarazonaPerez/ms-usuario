package com.example.plaza_de_comidas.adapters.driven.jpa.msql.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @Column(name="id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String name;
    @Column(name="lastname")
    private String lastName;
    @Column(name="identification_document")
    private String identificationDocument;
    private String phone;
    @Column(name="birthdate")
    private LocalDate birthDate;
    @Column(name = "gmail")
    private String gmail;
    private String password;
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private RolEntity idRol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((idRol.getName())));
    }
    @Override
    public String getUsername() {
        return getGmail();
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
