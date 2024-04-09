package com.app.invoices.security;

import com.app.invoices.entities.Role;
import com.app.invoices.repository.RoleRepository;
import com.app.invoices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.app.invoices.entities.User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    return new User(
        user.getEmail(),
        user.getPassword(),
        mapRoleToAuthority(roleRepository.findRoleById(user.getRoleId())));
    }

    private Collection<GrantedAuthority> mapRoleToAuthority(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Convert the role to a GrantedAuthority and add it to the collection
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }



}