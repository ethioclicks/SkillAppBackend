package com.ethioclicks.skilledApp.security.model;

import com.ethioclicks.skilledApp.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
public class CustomUserDetails implements UserDetails {

    User user;

    public CustomUserDetails(User user) {
        if(user != null) {
            this.user = user;
        } else {
            this.user = new User();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autorities=new HashSet<>();
        this.user.getRoles().forEach(ur->autorities.add(new Authority(ur.getName())));
        return autorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassWord();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
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
