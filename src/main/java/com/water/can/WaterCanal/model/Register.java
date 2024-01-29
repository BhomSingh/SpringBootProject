package com.water.can.WaterCanal.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// mark class as an Entity
@Entity
@Data
@NoArgsConstructor
@Table(name = "register")
public class Register implements UserDetails {

    // Defining book id as primary key
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String email = "";

    @Column
    private String userName = "";

    @Column
    private String mobileNo = "";

    @Column
    private String password = "";

    @Column
    private String confirmPassword = "";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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