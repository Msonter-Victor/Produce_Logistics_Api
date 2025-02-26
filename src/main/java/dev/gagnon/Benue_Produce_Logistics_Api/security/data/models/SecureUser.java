package dev.gagnon.Benue_Produce_Logistics_Api.security.data.models;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class SecureUser implements UserDetails {
    private final BioData user;
    @Getter
    private final String firstName;
    @Getter
    private final String lastName;
    @Getter
    private final String role;
    @Getter
    private final String mediaUrl;
    @Getter
    private final String phone;


    public SecureUser(BioData user) {
        this.user = user;
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = Objects.requireNonNull(user.getRoles().stream().findFirst().orElse(null)).name();
        this.mediaUrl = user.getImageUrl();
        this.phone = user.getPhone();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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


    @Override
    public String toString() {
        return "[" +
                "Username=" + user.getEmail() +
                ", Password=" + "[PROTECTED]" +
                ", Enabled=" + isEnabled() +
                ", AccountNonExpired=" + isAccountNonExpired() +
                ", CredentialsNonExpired=" + isCredentialsNonExpired() +
                ", AccountNonLocked=" + isAccountNonLocked() +
                ", Granted Authorities=" + getAuthorities() +
                ']';
    }
}