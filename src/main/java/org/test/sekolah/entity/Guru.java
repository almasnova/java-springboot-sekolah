package org.test.sekolah.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "guru")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guru implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nip")
    private String nip;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "password")
    private String password;

    @Column(name = "master")
    private boolean master;

    @Column(name = "date_modified")
    private Long dateModified;

    @Column(name = "date_created")
    private Long dateCreated;

    @Column(name = "date_last_login")
    private Long dateLastLogin;

    @Column(name = "deleted")
    private boolean deleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
