package com.scrumers.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.scrumers.api.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.scrumers.model.Role;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String name)
            throws UsernameNotFoundException {
        com.scrumers.model.User tmp = userService.getUser(name);
        if (tmp == null)
            throw new UsernameNotFoundException("User: " + name + " not found!");
        Collection<GrantedAuthority> cols = new ArrayList<GrantedAuthority>();
        List<Role> roles = userService.getUsersRoles(tmp.getId());
        cols.add(new SimpleGrantedAuthority(name));
        for (Role role : roles) {
            cols.add(new SimpleGrantedAuthority(role.getName()));
        }
        User user = new User(tmp.getName(), tmp.getPasswd(), cols);
        return user;
    }

}
