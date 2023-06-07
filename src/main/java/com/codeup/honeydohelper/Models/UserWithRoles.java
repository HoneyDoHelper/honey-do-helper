package com.codeup.honeydohelper.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class UserWithRoles extends Users implements UserDetails {

    public UserWithRoles(Users user) {
        super(user);  // Call the copy constructor defined in User
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        String roles = ""; // Since we're not using the authorization part of the component
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
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
