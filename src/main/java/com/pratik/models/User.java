package com.pratik.models;

import com.pratik.dto.UserRequestDto;
import com.pratik.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Table
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String authType;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public User(DefaultOidcUser user) {
        this.email = user.getEmail();
        this.authType = "oauth";
        this.firstName = user.getGivenName();
        this.lastName = user.getFamilyName();
        this.roles = List.of("USER");
    }

    public User(UserRequestDto user) {
        this.email = user.getEmail();
        this.password = PasswordUtil.encode(user.getPassword());
        this.authType = "basic";
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = List.of("USER");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return getEmail();
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
