package ba.unsa.etf.nwt.user_service.model;

import ba.unsa.etf.nwt.user_service.domain.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Data
public class UsernamePassword implements UserDetails {
    @Getter
    @Setter
    String username;

    @Getter
    @Setter
    String password;

    public UsernamePassword(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public UsernamePassword(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public UsernamePassword() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
