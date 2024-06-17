package com.ufpso.api.models;

import com.ufpso.api.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "Please provide the full name")
    @Size(min = 10, max = 200, message = "Fullname must have a minimum of ten character and a maximum of 200 characters")
    private String fullName;

    @NotNull(message = "please provide the document")
    @Size(min = 5, max = 10)
    private String document;

    @NotNull(message = "please provide the document type")
    private String documentType;

    @NotNull(message = "please provide the birthday")
    private LocalDate birthday;

    @NotNull(message = "please provide the phone number")
    @Size(min = 7, max = 10)
    private String phoneNumber;

    @NotNull(message = "please provide the email")
    @Email(message = "please provide the email")
    private String email;

    @NotNull(message = "please provide the password")
    @Size(min = 8)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
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